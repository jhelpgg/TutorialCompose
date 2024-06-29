package android.icu.util

class CalendarInstance : Calendar()
{
    override fun handleGetLimit(field: Int, limitType: Int): Int = 0

    override fun handleComputeMonthStart(eyear: Int, month: Int, useMonth: Boolean) = 0

    override fun handleGetExtendedYear(): Int = 0
}