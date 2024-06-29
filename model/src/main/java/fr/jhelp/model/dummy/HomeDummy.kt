package fr.jhelp.model.dummy

import fr.jhelp.model.action.home.HomeAction
import fr.jhelp.model.data.HomeData
import fr.jhelp.model.shared.HomeModel
import fr.jhelp.tool.tasks.observable.Observable
import fr.jhelp.tool.tasks.observable.ObservableSource

class HomeDummy(homeDataSource: Observable<HomeData>, private val onAction: (HomeAction) -> Unit = {}) :
    HomeModel
{
    constructor(mainData: HomeData, action: (HomeAction) -> Unit = {}) :
            this(ObservableSource<HomeData>(mainData).observable, action)

    override val data: Observable<HomeData> = homeDataSource

    override fun action(action: HomeAction)
    {
        this.onAction(action)
    }
}