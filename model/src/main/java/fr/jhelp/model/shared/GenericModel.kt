package fr.jhelp.model.shared

import androidx.compose.runtime.State

interface GenericModel<DATA:Any, ACTION:Any>
{
    val data : State<DATA>

    fun action(action : ACTION)
}