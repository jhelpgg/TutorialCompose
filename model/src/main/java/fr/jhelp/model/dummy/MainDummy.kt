package fr.jhelp.model.dummy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.model.action.main.MainAction
import fr.jhelp.model.data.MainData
import fr.jhelp.model.shared.MainModel

class MainDummy(mainDataSource:State<MainData>, private val onAction: (MainAction)->Unit={}) : MainModel
{
    constructor(mainData:MainData, action:(MainAction)->Unit={}) :
            this(mutableStateOf(mainData), action)

    override val data: State<MainData> = mainDataSource

    override fun action(action: MainAction)
    {
        this.onAction(action)
    }
}