package fr.jhelp.model.implementation

import fr.jhelp.model.shared.GenericModel
import fr.jhelp.tool.tasks.Mutex
import fr.jhelp.tool.tasks.observable.Observable
import fr.jhelp.tool.tasks.observable.ObservableSource

internal abstract class GenericImplementation<DATA : Any, ACTION : Any>(initialData: DATA) : GenericModel<DATA, ACTION>
{
    private val mutex = Mutex()
    private val dataSource = ObservableSource(initialData)
    final override val data: Observable<DATA> = this.dataSource.observable

    protected fun update(action: DATA.() -> DATA)
    {
        this.mutex {
            val value = this.dataSource.value.action()

            if (this.dataSource.value != value)
            {
                this.dataSource.value = value
            }
        }
    }
}