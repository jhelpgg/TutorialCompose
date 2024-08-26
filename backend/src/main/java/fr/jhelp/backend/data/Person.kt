package fr.jhelp.backend.data

import android.icu.util.Calendar
import fr.jhelp.tool.dateString
import java.util.Objects

data class Person internal constructor(val name: String = "", val birthDate: Calendar = Calendar.getInstance(), internal var databaseId: Long = -1L)
{
    constructor(name: String, birthDate: Calendar) : this(name, birthDate, -1L)

    override fun equals(other: Any?): Boolean =
        when
        {
            this === other                                  -> true

            null == other || other !is Person               -> false

            this.databaseId >= 0L && other.databaseId >= 0L -> this.databaseId == other.databaseId

            else                                            -> this.name == other.name && this.birthDate.timeInMillis == other.birthDate.timeInMillis
        }

    override fun hashCode(): Int =
        when
        {
            this.databaseId >= 0L -> Objects.hash(this.databaseId)

            else                  -> Objects.hash(this.name, this.birthDate.timeInMillis)
        }

    override fun toString(): String =
        "${this.name} : ${this.birthDate.dateString}"
}
