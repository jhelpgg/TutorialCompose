package fr.jhelp.model.dummy

import fr.jhelp.model.action.main.MainAction
import fr.jhelp.model.data.MainData
import fr.jhelp.model.shared.MainModel
import fr.jhelp.tool.tasks.observable.Observable
import fr.jhelp.tool.tasks.observable.ObservableSource

class MainDummy(mainDataSource: Observable<MainData>, private val onAction: (MainAction) -> Unit = {}) : MainModel
{
    constructor(mainData: MainData, action: (MainAction) -> Unit = {}) :
            this(ObservableSource(mainData).observable, action)

    override val data: Observable<MainData> = mainDataSource

    override fun action(action: MainAction)
    {
        this.onAction(action)
    }
}