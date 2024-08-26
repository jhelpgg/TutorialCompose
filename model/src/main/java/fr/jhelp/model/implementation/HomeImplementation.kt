package fr.jhelp.model.implementation

import android.icu.util.Calendar
import fr.jhelp.backend.data.Person
import fr.jhelp.backend.event.persons.PersonsEvent
import fr.jhelp.backend.event.persons.PersonsEventNewPerson
import fr.jhelp.backend.shared.PersonsModel
import fr.jhelp.model.action.home.HomeAction
import fr.jhelp.model.action.home.HomeActionCallData
import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.tool.age
import fr.jhelp.tool.collectFlow
import fr.jhelp.tool.providers.provided
import fr.jhelp.tool.years

internal class HomeImplementation :
    GenericImplementation<HomeData, HomeAction>(HomeData(information = "Information")),
    HomeModel
{
    companion object
    {
        fun randomDate(): Calendar
        {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis -= (Math.random() * 99L.years).toLong()
            return calendar
        }
    }

    private val personsModel: PersonsModel by provided<PersonsModel>()

    init
    {
        this.personsModel.personsEvent.collectFlow { personsEvent -> this.onPersonEvent(personsEvent) }
    }

    override fun action(action: HomeAction)
    {
        when (action)
        {
            is HomeActionChangeInformation ->
                this.update { this.copy(information = action.information) }

            is HomeActionCallData          ->
                this.personsModel.add(Person("Person N ${(Math.random() * 1000).toInt()}", HomeImplementation.randomDate()))
        }
    }

    private fun onPersonEvent(personsEvent: PersonsEvent)
    {
        when (personsEvent)
        {
            is PersonsEventNewPerson ->
                this.update { this.copy(information = personsEvent.person.name + " : " + personsEvent.person.birthDate.age + " years old") }
        }
    }
}
