package fr.jhelp.tool.tasks.observable

import fr.jhelp.tool.tasks.Mutex

/**
 * Source of observable value
 */
class ObservableSource<T : Any>(initialValue: T)
{
    /**
     * Mutex to protect value
     */
    private val mutex = Mutex()

    /**
     * Current value
     */
    var value: T = initialValue
        set(value)
        {
            this.mutex {
                if (field != value)
                {
                    field = value
                    this.observable.publish(value)
                }
            }
        }

    /**
     * Observable to observe value change
     */
    val observable = Observable<T>(initialValue)
}
