package fr.jhelp.backend.shared

import kotlinx.coroutines.flow.StateFlow

interface GenericModel<DATA:Any, ACTION:Any>
{
    val data : StateFlow<DATA>

    fun action(action : ACTION)
}