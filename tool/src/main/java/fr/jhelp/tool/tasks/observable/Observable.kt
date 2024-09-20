package fr.jhelp.tool.tasks.observable

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import fr.jhelp.tool.tasks.Mutex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Observable value
 */
class Observable<T : Any> internal constructor(initialValue: T)
{
    private val mutex = Mutex()

    /**
     * Value of the observable
     */
    private var value: T = initialValue

    /**
     * Observers of the observable
     */
    private val observers = ArrayList<Observer<T>>()

    /**
     * Compose state of the observable
     */
    private val mutableState = mutableStateOf(initialValue)

    /**
     * Compose state of the observable
     */
    val composeState: State<T> = this.mutableState

    /**
     * Observe the observable with given action
     *
     * The action is called with the current value of the observable and each time the value change
     *
     * @param action Action to call each time the value change
     * @return Observer to be able to stop to observe
     */
    fun observeBy(action: (T) -> Unit): Observer<T>
    {
        val observer = Observer<T>(action, this)

        CoroutineScope(Dispatchers.Default).launch {
            delay(1)
            action(this@Observable.value)

            this@Observable.mutex { this@Observable.observers.add(observer) }
        }

        return observer
    }

    /**
     * Do an action when a condition is true
     *
     * The action is called with the value of the observable when the condition is true
     * The action is called only once
     *
     * @param condition Condition to check
     * @param action    Action to do when the condition is true
     */
    fun whenConditionDo(condition: (T) -> Boolean, action: (T) -> Unit)
    {
        val whenConditionDo = WhenConditionDo(this, condition, action)
        whenConditionDo.launch()
    }

    /**
     * Publish the given value
     */
    internal fun publish(value: T)
    {
        this.value = value

        MainScope().launch {
            this@Observable.mutableState.value = value
        }

        CoroutineScope(Dispatchers.Default).launch {
            this@Observable.mutex {
                for (observer in this@Observable.observers)
                {
                    observer.publish(value)
                }
            }
        }
    }

    /**
     * An observer stopped to observe
     *
     * @param observer Observer that stopped to observe
     */
    internal fun stopObserve(observer: Observer<T>)
    {
        this@Observable.mutex { this.observers.remove(observer) }
    }
}