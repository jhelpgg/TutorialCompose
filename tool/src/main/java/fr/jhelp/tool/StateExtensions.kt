package fr.jhelp.tool

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.collectFlow(action:(T)->Unit)
{
    CoroutineScope(Dispatchers.Default).launch { this@collectFlow.collect(action) }
}

fun <T> SharedFlow<T>.collectFlow(action:(T)->Unit)
{
    CoroutineScope(Dispatchers.Default).launch { this@collectFlow.collect(action) }
}