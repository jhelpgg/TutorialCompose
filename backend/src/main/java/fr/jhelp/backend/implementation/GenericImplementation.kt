package fr.jhelp.backend.implementation

import fr.jhelp.backend.shared.GenericModel
import fr.jhelp.tool.tasks.Mutex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal abstract class GenericImplementation<DATA : Any, ACTION : Any>(initialData: DATA) : GenericModel<DATA, ACTION>
{
    private val mutex = Mutex()
    private val dataMutable = MutableStateFlow(initialData)
    final override val data: StateFlow<DATA> = this.dataMutable.asStateFlow()

    protected fun update(action: DATA.() -> DATA)
    {
        this.mutex {
            val value = this.dataMutable.value.action()

            if (this.dataMutable.value != value)
            {
                CoroutineScope(Dispatchers.Default).launch { this@GenericImplementation.dataMutable.value = value }
            }
        }
    }
}