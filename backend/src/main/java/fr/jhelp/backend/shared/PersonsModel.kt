package fr.jhelp.backend.shared

import fr.jhelp.backend.data.Person
import fr.jhelp.backend.event.persons.PersonsEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PersonsModel
{
    val numberPersons: StateFlow<Int>
    val personsEvent: SharedFlow<PersonsEvent>

    operator fun get(index: Int): Person

    fun add(person: Person)
}