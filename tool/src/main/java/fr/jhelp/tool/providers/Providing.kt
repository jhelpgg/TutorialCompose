package fr.jhelp.tool.providers

inline fun <reified T : Any> provideSingle(noinline producer: () -> T): Unit =
    provideSingle("", producer)

inline fun <reified T : Any> provideSingle(qualifier: String, noinline producer: () -> T)
{
    ProviderManager.provide(producer, T::class, qualifier, true)
}

inline fun <reified T : Any> provideMultiple(noinline producer: () -> T): Unit =
    provideMultiple("", producer)

inline fun <reified T : Any> provideMultiple(qualifier: String, noinline producer: () -> T)
{
    ProviderManager.provide(producer, T::class, qualifier, false)
}

inline fun <reified T : Any> forget(qualifier: String = "")
{
    ProviderManager.forget(T::class, qualifier)
}

inline fun <reified T : Any> provided(qualifier: String = ""): Provided<T> =
    Provided<T>("${T::class.qualifiedName}:$qualifier")

inline fun <reified T : Any> isProvided(qualifier: String = ""): Boolean =
    ProviderManager.isProvided("${T::class.qualifiedName}:$qualifier")
