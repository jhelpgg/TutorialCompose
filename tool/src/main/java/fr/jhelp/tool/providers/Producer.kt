package fr.jhelp.tool.providers

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Producer of value
 */
internal class Producer<T : Any>(private val single: Boolean,
                                 private val producer: () -> T)
{
    /**
     * Indicates if the value is already created
     */
    private val created = AtomicBoolean(false)
    /**
     * Created value
     */
    private lateinit var value: T

    /**
     * Value produced
     */
    fun value(): T =
        if (this.single)
        {
            if (this.created.compareAndSet(false, true))
            {
                this.value = this.producer()
            }

            this.value
        }
        else
        {
            this.producer()
        }
}