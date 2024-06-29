package fr.jhelp.model.shared

import fr.jhelp.tool.tasks.observable.Observable

interface GenericModel<DATA : Any, ACTION : Any>
{
    val data: Observable<DATA>

    fun action(action: ACTION)
}