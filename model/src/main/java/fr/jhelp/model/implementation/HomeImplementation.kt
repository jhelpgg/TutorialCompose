package fr.jhelp.model.implementation

import fr.jhelp.backend.Backend
import fr.jhelp.model.action.home.HomeAction
import fr.jhelp.model.action.home.HomeActionCallData
import fr.jhelp.model.action.home.HomeActionChangeInformation
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.tool.collectFlow
import java.util.concurrent.atomic.AtomicBoolean

internal class HomeImplementation :
        GenericImplementation<HomeData, HomeAction>(HomeData(information = "Information")),
        HomeModel
{
    private val registered = AtomicBoolean(false)

    override fun action(action: HomeAction)
    {
        if (this.registered.compareAndSet(false, true))
        {
            Backend.data.collectFlow { information ->
                this.update { this.copy(information = information) }
            }
        }

        when (action)
        {
            is HomeActionChangeInformation ->
                this.update { this.copy(information = action.information) }

            is HomeActionCallData          -> Backend.initialize()
        }
    }
}
