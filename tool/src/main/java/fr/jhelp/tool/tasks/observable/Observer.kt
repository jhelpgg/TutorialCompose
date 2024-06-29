package fr.jhelp.tool.tasks.observable

/**
 * Observer of observable value
 */
class Observer<T : Any> internal constructor(private val observer: (T) -> Unit,
                                             private val parent: Observable<T>)
{
    /**
     * Stop the observation
     */
    fun stopObserve()
    {
        this.parent.stopObserve(this)
    }

    /**
     * Publish the value
     */
    internal fun publish(value: T)
    {
        this.observer(value)
    }
}