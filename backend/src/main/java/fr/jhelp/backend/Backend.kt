package fr.jhelp.backend

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object Backend
{
    private val dataMutable = MutableStateFlow<String>("First")
    val data : StateFlow<String> = this.dataMutable.asStateFlow()

    fun initialize()
    {
        CoroutineScope(Dispatchers.Default).launch{
            delay(5000)
            this@Backend.dataMutable.value = "New"
        }
    }
}