package fr.jhelp.tool.providers

import kotlin.reflect.KClass

object ProviderManager
{
    private val providedProducers = HashMap<String, Producer<*>>()

    @Synchronized
    fun <T : Any> provide(producer: () -> T, kClass: KClass<T>, qualifier: String, single: Boolean)
    {
        this.providedProducers["${kClass.qualifiedName}:$qualifier"] = Producer(single, producer)
    }

    @Synchronized
    fun <T : Any> forget(kClass: KClass<T>, qualifier: String)
    {
        this.providedProducers.remove("${kClass.qualifiedName}:$qualifier")
    }

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

    @Synchronized
    fun isProvided(qualifier: String): Boolean =
        qualifier in this.providedProducers
}