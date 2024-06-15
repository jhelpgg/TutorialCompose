package fr.jhelp.model.implementation

import fr.jhelp.model.action.main.MainActionChangeScreen
import fr.jhelp.model.data.Screen
import fr.jhelp.model.shared.MainModel
import fr.jhelp.model.shared.SPLASH_DURATION
import fr.jhelp.model.shared.SplashModel
import fr.jhelp.tool.providers.provided
import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class SplashImplementation : SplashModel
{
    private val launched = AtomicBoolean(false)
    private val mainModel by provided<MainModel>()

    override fun splashShow()
    {
        if(this.launched.compareAndSet(false, true))
        {
            CoroutineScope(Dispatchers.Default).launch {
                delay(SPLASH_DURATION)
                this@SplashImplementation.mainModel.action(MainActionChangeScreen(Screen.HOME))
                this@SplashImplementation.launched.set(false)
            }
        }
    }
}
