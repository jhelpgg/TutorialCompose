package fr.jhelp.composetutorial

import android.app.Application
import fr.jhelp.model.provideModels

class MAinApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        provideModels()
    }
}