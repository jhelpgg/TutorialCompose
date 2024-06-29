package fr.jhelp.tool.providers

import kotlin.reflect.KProperty

/**
 * Provide a value
 * @param identifier Identifier of the value
 */
@Suppress("UNCHECKED_CAST")
class Provided<T>(private val identifier: String)
{
    operator fun getValue(thisRef: Any, property: KProperty<*>): T =
        ProviderManager.provided(this.identifier) as T
}