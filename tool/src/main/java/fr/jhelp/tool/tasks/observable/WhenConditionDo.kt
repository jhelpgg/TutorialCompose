package fr.jhelp.tool.tasks.observable

/**
 * Launch an action when a condition is true
 */
internal class WhenConditionDo<T : Any>(private val observable: Observable<T>,
                                        private val condition: (T) -> Boolean,
                                        private val action: (T) -> Unit)
{
    /**
     * Observer to stop when condition is true
     */
    private lateinit var observer: Observer<T>

    /**
     * Launch the observation
     */
    fun launch()
    {
        this.observer =
            this.observable.observeBy { value ->
                if (this.condition(value))
                {
                    this.action(value)
                    this.observer.stopObserve()
                }
            }
    }
}