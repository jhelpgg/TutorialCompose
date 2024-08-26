package fr.jhelp.tool

import android.icu.util.Calendar
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

val Calendar.age: Int
    get()
    {
        val now = Calendar.getInstance()
        var age = now.get(Calendar.YEAR) - this.get(Calendar.YEAR)

        if (now.get(Calendar.MONTH) > this.get(Calendar.MONTH) ||
            now.get(Calendar.MONTH) == this.get(Calendar.MONTH) && now.get(Calendar.DATE) > this.get(Calendar.DATE))
        {
            age--
        }

        return age
    }

class CalendarExtensionsTests
{
    companion object
    {
        @JvmStatic
        @BeforeClass
        fun initialize()
        {
            println(Calendar.getInstance())
        }
    }

    @Test
    fun testAge()
    {
        val beforeMonth = Calendar.getInstance()
        beforeMonth.add(Calendar.YEAR, -5)
        beforeMonth.add(Calendar.MONTH, -1)
        Assert.assertEquals(4, beforeMonth.age)
        val afterMonth = Calendar.getInstance()
        afterMonth.add(Calendar.YEAR, -5)
        afterMonth.add(Calendar.MONTH, 1)
        Assert.assertEquals(5, afterMonth.age)
        val today = Calendar.getInstance()
        Assert.assertEquals(0, today.age)
        val beforeDay = Calendar.getInstance()
        beforeDay.add(Calendar.YEAR, -5)
        beforeDay.add(Calendar.DATE, -1)
        Assert.assertEquals(4, beforeDay.age)
        val afterDay = Calendar.getInstance()
        afterDay.add(Calendar.YEAR, -5)
        afterDay.add(Calendar.DATE, 1)
        Assert.assertEquals(5, afterDay.age)
        val sameDay = Calendar.getInstance()
        sameDay.add(Calendar.YEAR, -5)
        Assert.assertEquals(5, afterDay.age)
    }
}