package fr.jhelp.model

import fr.jhelp.backend.initializeBackend
import fr.jhelp.model.implementation.HomeImplementation
import fr.jhelp.model.implementation.MainImplementation
import fr.jhelp.model.implementation.SplashImplementation
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.model.shared.MainModel
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provideSingle

fun initializeModels()
{
    initializeBackend()
    provideSingle<MainModel> { MainImplementation() }
    provideSingle<SplashModel> { SplashImplementation() }
    provideSingle<HomeModel> { HomeImplementation() }
}