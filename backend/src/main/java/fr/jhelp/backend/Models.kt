package fr.jhelp.backend

import fr.jhelp.backend.implementation.PersonsImplementation
import fr.jhelp.backend.shared.PersonsModel
import fr.jhelp.tool.providers.provideSingle

fun initializeBackend()
{
    provideSingle<PersonsModel> { PersonsImplementation() }
}