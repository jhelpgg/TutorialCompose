package fr.jhelp.tool

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Collect state flow in a coroutine
 *
 * Each time a value is emitted, the action is done
 *
 * @param action Action to do for each value
 */
fun <T> StateFlow<T>.collectFlow(action:(T)->Unit)
{
    CoroutineScope(Dispatchers.Default).launch { this@collectFlow.collect(action) }
}

/**
 * Collect shared flow in a coroutine
 *
 * Each time a value is emitted, the action is done
 *
 * @param action Action to do for each value
 */
fun <T> SharedFlow<T>.collectFlow(action:(T)->Unit)
{
    CoroutineScope(Dispatchers.Default).launch { this@collectFlow.collect(action) }
}