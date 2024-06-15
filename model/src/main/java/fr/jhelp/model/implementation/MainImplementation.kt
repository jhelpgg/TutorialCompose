package fr.jhelp.model.implementation

import fr.jhelp.model.action.main.MainAction
import fr.jhelp.model.action.main.MainActionChangeScreen
import fr.jhelp.model.data.MainData
import fr.jhelp.model.data.Screen
import fr.jhelp.model.shared.MainModel

internal class MainImplementation :
        GenericImplementation<MainData, MainAction>(MainData(screen = Screen.SPLASH)),
        MainModel
{
    override fun action(action: MainAction)
    {
        when (action)
        {
            is MainActionChangeScreen ->
                this.update { this.copy(screen = action.screen) }
        }
    }
}