package fr.jhelp.backend.event.persons

import fr.jhelp.backend.data.Person

data class PersonsEventNewPerson(val person: Person) : PersonsEvent
