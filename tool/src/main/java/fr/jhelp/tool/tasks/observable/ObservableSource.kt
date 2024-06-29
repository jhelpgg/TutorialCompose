package fr.jhelp.tool.tasks.observable

import fr.jhelp.tool.tasks.Mutex

class ObservableSource<T : Any>(initialValue: T)
{
    private val mutex = Mutex()
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

    val observable = Observable<T>(initialValue)
}
