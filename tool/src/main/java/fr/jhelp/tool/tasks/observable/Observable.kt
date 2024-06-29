package fr.jhelp.tool.tasks.observable

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Observable<T : Any> internal constructor(initialValue: T)
{
    private var value: T = initialValue
    private val observers = ArrayList<Observer<T>>()
    private val mutableState = mutableStateOf(initialValue)
    val composeState: State<T> = this.mutableState

    fun observeBy(action: (T) -> Unit): Observer<T>
    {
        val observer = Observer<T>(action, this)

        CoroutineScope(Dispatchers.Default).launch {
            delay(1)
            action(this@Observable.value)

            synchronized(this@Observable.observers) {
                this@Observable.observers.add(observer)
            }
        }

        return observer
    }

    fun whenConditionDo(condition: (T) -> Boolean, action: (T) -> Unit)
    {
        val whenConditionDo = WhenConditionDo(this, condition, action)
        whenConditionDo.launch()
    }

    internal fun publish(value: T)
    {
        this.value = value

        MainScope().launch {
            this@Observable.mutableState.value = value
        }

        CoroutineScope(Dispatchers.Default).launch {
            synchronized(this@Observable.observers) {
                for (observer in this@Observable.observers)
                {
                    observer.publish(value)
                }
            }
        }
    }

    internal fun stopObserve(observer: Observer<T>)
    {
        synchronized(this.observers) {
            this.observers.remove(observer)
        }
    }
}