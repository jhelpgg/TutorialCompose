package fr.jhelp.tool

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

val Calendar.age : Int
    get()
    {
        val now = Calendar.getInstance()
        var age = now.get(Calendar.YEAR) - this.get(Calendar.YEAR)

        if (now.get(Calendar.MONTH) < this.get(Calendar.MONTH) ||
            now.get(Calendar.MONTH) == this.get(Calendar.MONTH) && now.get(Calendar.DATE) < this.get(Calendar.DATE))
        {
            age --
        }

       return age
    }

val dateFormatter = SimpleDateFormat("yyyy/MM/dd")

val Calendar.dateString : String get() = dateFormatter.format(this.time)
