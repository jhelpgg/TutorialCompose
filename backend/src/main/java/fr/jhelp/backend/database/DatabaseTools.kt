package fr.jhelp.backend.database

import android.content.ContentValues
import android.database.Cursor
import android.icu.util.Calendar

internal fun Cursor.getCalendar(columnIndex: Int): Calendar
{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.getLong(columnIndex)
    return calendar
}

internal fun ContentValues.put(column: String, calendar: Calendar)
{
    this.put(column, calendar.timeInMillis)
}