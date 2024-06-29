package fr.jhelp.tool.tasks.observable

class Observer<T : Any> internal constructor(private val observer: (T) -> Unit,
                                             private val parent: Observable<T>)
{
    fun stopObserve()
    {
        this.parent.stopObserve(this)
    }

    internal fun publish(value: T)
    {
        this.observer(value)
    }
}