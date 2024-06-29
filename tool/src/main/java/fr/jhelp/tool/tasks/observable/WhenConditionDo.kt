package fr.jhelp.tool.tasks.observable

internal class WhenConditionDo<T : Any>(private val observable: Observable<T>,
                                        private val condition: (T) -> Boolean,
                                        private val action: (T) -> Unit)
{
    private lateinit var observer: Observer<T>

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