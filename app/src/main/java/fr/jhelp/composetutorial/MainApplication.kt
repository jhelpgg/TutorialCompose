package fr.jhelp.composetutorial

import android.app.Application
import android.content.Context
import fr.jhelp.model.initializeModels
import fr.jhelp.tool.providers.provideSingle

class MainApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        val applicationContext = this.applicationContext
        provideSingle<Context> { applicationContext }
        initializeModels()
    }
}