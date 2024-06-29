package fr.jhelp.tool.tasks

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

object ImmediateDispatcher : CoroutineDispatcher()
{
    override fun dispatch(context: CoroutineContext, block: Runnable)
    {
        block.run()
    }
}