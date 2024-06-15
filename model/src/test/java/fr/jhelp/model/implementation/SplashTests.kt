package fr.jhelp.model.implementation

import fr.jhelp.model.action.main.MainActionChangeScreen
import fr.jhelp.model.data.MainData
import fr.jhelp.model.data.Screen
import fr.jhelp.model.dummy.MainDummy
import fr.jhelp.model.shared.MainModel
import fr.jhelp.model.shared.SPLASH_DURATION
import fr.jhelp.tool.providers.provideSingle
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

class SplashTests
{
    companion object
    {
        val screen = AtomicReference<Screen>(Screen.SPLASH)

        @JvmStatic
        @BeforeClass
        fun initialize()
        {
            provideSingle <MainModel> {
                MainDummy(MainData(screen = Screen.SPLASH))
                {mainAction ->
                    when(mainAction)
                    {
                        is MainActionChangeScreen -> this.screen.set(mainAction.screen)
                    }
                }
            }
        }
    }

    @Test
    fun splashTimeOut()
    {
        val splashModel = SplashImplementation()
        splashModel.splashShow()
        Assert.assertEquals(Screen.SPLASH, SplashTests.screen.get())

        Thread.sleep(SPLASH_DURATION + 1024)

        Assert.assertEquals(Screen.HOME, SplashTests.screen.get())
    }
}