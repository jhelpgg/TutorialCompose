package fr.jhelp.model.dummy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.model.action.home.HomeAction
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.shared.HomeModel

class HomeDummy(homeDataSource: State<HomeData>, private val onAction: (HomeAction) -> Unit = {}) :
        HomeModel
{
    constructor(mainData: HomeData, action: (HomeAction) -> Unit = {}) :
            this(mutableStateOf(mainData), action)

    override val data: State<HomeData> = homeDataSource

    override fun action(action: HomeAction)
    {
        this.onAction(action)
    }
}