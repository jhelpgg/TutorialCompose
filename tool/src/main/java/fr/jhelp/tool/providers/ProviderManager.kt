package fr.jhelp.tool.providers

import kotlin.reflect.KClass

/**
 * Manage the providers
 */
object ProviderManager
{
    /**
     * Producers provided
     */
    private val providedProducers = HashMap<String, Producer<*>>()

    /**
     * Provide a producer
     */
    @Synchronized
    fun <T : Any> provide(producer: () -> T, kClass: KClass<T>, qualifier: String, single: Boolean)
    {
        this.providedProducers["${kClass.qualifiedName}:$qualifier"] = Producer(single, producer)
    }

    /**
     * Forget a producer
     */
    @Synchronized
    fun <T : Any> forget(kClass: KClass<T>, qualifier: String)
    {
        this.providedProducers.remove("${kClass.qualifiedName}:$qualifier")
    }

    /**
     * Provide a value
     */
    @Synchronized
    @Throws(IllegalArgumentException::class)
    internal fun provided(qualifier: String): Any =
        try
        {
            this.providedProducers.getValue(qualifier).value()
        }
        catch (exception: Exception)
        {
            throw IllegalArgumentException("No definition for $qualifier", exception)
        }

    /**
     * Indicates if a value is provided
     */
    @Synchronized
    fun isProvided(qualifier: String): Boolean =
        qualifier in this.providedProducers
}