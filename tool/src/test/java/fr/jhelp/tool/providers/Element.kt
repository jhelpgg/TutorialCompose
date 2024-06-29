package fr.jhelp.tool.providers

import java.util.concurrent.atomic.AtomicInteger

data class Element(val information: String)
{
    companion object
    {
        private val nextID = AtomicInteger(0)
    }

    val id = Element.nextID.incrementAndGet()
}
