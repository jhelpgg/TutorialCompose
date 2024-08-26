package fr.jhelp.model.implementation

import fr.jhelp.model.action.main.MainActionChangeScreen
import fr.jhelp.model.data.Screen
import fr.jhelp.tool.tasks.ImmediateDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test

class MainTests
{
    @Test
    fun changeScreen()
    {
        Dispatchers.setMain(ImmediateDispatcher)
        val mainModel = MainImplementation()
        Assert.assertEquals(Screen.SPLASH, mainModel.data.composeState.value.screen)
        mainModel.action(MainActionChangeScreen(screen = Screen.HOME))
        Assert.assertEquals(Screen.HOME, mainModel.data.composeState.value.screen)
    }
}