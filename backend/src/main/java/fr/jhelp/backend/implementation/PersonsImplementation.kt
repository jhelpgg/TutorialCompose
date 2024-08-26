package fr.jhelp.backend.implementation

import android.util.Log
import fr.jhelp.backend.data.Person
import fr.jhelp.backend.database.Database
import fr.jhelp.backend.event.persons.PersonsEvent
import fr.jhelp.backend.event.persons.PersonsEventNewPerson
import fr.jhelp.backend.shared.PersonsModel
import fr.jhelp.tool.tasks.ImmediateDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

internal class PersonsImplementation : PersonsModel
{
    private val collecting = AtomicBoolean(true)
    private val numberPersonsMutable = MutableStateFlow<Int>(0)
    override val numberPersons: StateFlow<Int> = this.numberPersonsMutable.asStateFlow()
    private val personsEventMutable = MutableSharedFlow<PersonsEvent>()
    override val personsEvent: SharedFlow<PersonsEvent> = this.personsEventMutable.asSharedFlow()
    private val persons = ArrayList<Person>()
    private val waitingToAdd = ArrayList<Person>()

    init
    {
        Database.allPerson(this::collect, this::collectFinished)
    }

    override fun get(index: Int): Person =
        synchronized(this.persons) { this.persons[index] }

    override fun add(person: Person)
    {
        synchronized(this.persons)
        {
            if (this.collecting.get())
            {
                this.waitingToAdd.add(person)
            }
            else
            {
                this.addUnsafe(person)
            }
        }

        Database.addOrUpdatePerson(person)
    }

    private fun collect(person: Person)
    {
        Log.d("REMOVE_ME", "person = $person")
        synchronized(this.persons) { this.addUnsafe(person) }
    }

    private fun collectFinished()
    {
        synchronized(this.persons)
        {
            for (person in this.waitingToAdd)
            {
                this.addUnsafe(person)
            }

            this.waitingToAdd.clear()
            this.collecting.set(false)
        }
    }

    private fun addUnsafe(person: Person)
    {
        this.persons.add(person)
        val size = this.persons.size

        CoroutineScope(ImmediateDispatcher).launch {
            this@PersonsImplementation.numberPersonsMutable.value = size
            this@PersonsImplementation.personsEventMutable.emit(PersonsEventNewPerson(person))
        }
    }
}