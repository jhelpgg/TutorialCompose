package fr.jhelp.model.implementation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.model.shared.GenericModel
import fr.jhelp.tool.tasks.Mutex
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

internal abstract class GenericImplementation<DATA : Any, ACTION : Any>(initialData:DATA) : GenericModel<DATA, ACTION>
{
    private val mutex = Mutex()
    private val dataMutable = mutableStateOf(initialData)
    final override val data: State<DATA> = this.dataMutable

    protected fun update(action:DATA.()->DATA)
    {
        this.mutex {
            val value = this.dataMutable.value.action()

            if(this.dataMutable.value != value)
            {
                MainScope().launch { this@GenericImplementation.dataMutable.value = value }
            }
        }
    }
}