package fr.jhelp.tool.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

/**
 * Dispatcher that execute the task immediately
 */
object ImmediateDispatcher : CoroutineDispatcher()
{
    /**
     * Dispatch the task immediately
     */
    override fun dispatch(context: CoroutineContext, block: Runnable)
    {
        block.run()
    }
}