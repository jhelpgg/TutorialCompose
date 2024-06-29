package android.icu.util

import java.util.Date

abstract class Calendar
{
    companion object
    {
        const val ERA = 0
        const val YEAR = 1
        const val MONTH = 2
        const val WEEK_OF_YEAR = 3
        const val WEEK_OF_MONTH = 4
        const val DATE = 5
        const val DAY_OF_MONTH = 5
        const val DAY_OF_YEAR = 6
        const val DAY_OF_WEEK = 7
        const val DAY_OF_WEEK_IN_MONTH = 8
        const val AM_PM = 9
        const val HOUR = 10
        const val HOUR_OF_DAY = 11
        const val MINUTE = 12
        const val SECOND = 13
        const val MILLISECOND = 14
        const val ZONE_OFFSET = 15
        const val DST_OFFSET = 16
        const val YEAR_WOY = 17
        const val DOW_LOCAL = 18
        const val EXTENDED_YEAR = 19
        const val JULIAN_DAY = 20
        const val MILLISECONDS_IN_DAY = 21
        const val IS_LEAP = 22
        const val BASE_FIELD_COUNT = 23
        const val MAX_FIELD_COUNT = 32
        const val SUNDAY = 1
        const val MONDAY = 2
        const val TUESDAY = 3
        const val WEDNESDAY = 4
        const val THURSDAY = 5
        const val FRIDAY = 6
        const val SATURDAY = 7
        const val JANUARY = 0
        const val FEBRUARY = 1
        const val MARCH = 2
        const val APRIL = 3
        const val MAY = 4
        const val JUNE = 5
        const val JULY = 6
        const val AUGUST = 7
        const val SEPTEMBER = 8
        const val OCTOBER = 9
        const val NOVEMBER = 10
        const val DECEMBER = 11
        const val UNDECIMBER = 12
        const val AM = 0
        const val PM = 1
        const val WEEKDAY = 0
        const val WEEKEND = 1
        const val WEEKEND_ONSET = 2
        const val WEEKEND_CEASE = 3
        const val WALLTIME_LAST = 0
        const val WALLTIME_FIRST = 1
        const val WALLTIME_NEXT_VALID = 2

        @JvmStatic
        fun getInstance(): Calendar
        {
            println("CalendarInstance")
            return CalendarInstance()
        }
    }

    private val javaCalendar = java.util.Calendar.getInstance()

    var time: Date
        get() = this.javaCalendar.time
        set(value)
        {
            this.javaCalendar.time = value
        }

    var timeInMillis: Long
        get() = this.javaCalendar.timeInMillis
        set(value)
        {
            this.javaCalendar.timeInMillis = value
        }

    operator fun get(field: Int): Int =
        this.javaCalendar.get(field)

    operator fun set(field: Int, value: Int)
    {
        this.javaCalendar.set(field, value)
    }

    fun add(field: Int, amount: Int)
    {
        this.javaCalendar.add(field, amount)
    }

    fun roll(field: Int, amount: Int)
    {
        this.javaCalendar.roll(field, amount)
    }

    fun set(year: Int, month: Int, date: Int)
    {
        this.javaCalendar.set(year, month, date)
    }

    fun set(year: Int, month: Int, date: Int, hour: Int, minute: Int)
    {
        this.javaCalendar.set(year, month, date, hour, minute)
    }

    fun set(year: Int, month: Int, date: Int, hour: Int, minute: Int, second: Int)
    {
        this.javaCalendar.set(year, month, date, hour, minute, second)
    }

    abstract fun handleGetLimit(field: Int, limitType: Int): Int
    abstract fun handleComputeMonthStart(eyear: Int, month: Int, useMonth: Boolean): Int
    abstract fun handleGetExtendedYear(): Int
}