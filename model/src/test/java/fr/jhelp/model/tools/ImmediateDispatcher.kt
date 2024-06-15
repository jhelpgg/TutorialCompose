package fr.jhelp.model.tools

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable

object ImmediateDispatcher : CoroutineDispatcher()
{
    override fun dispatch(context: CoroutineContext, block: Runnable)
    {
        block.run()
    }
}