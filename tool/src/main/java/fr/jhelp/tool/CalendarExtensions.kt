package fr.jhelp.tool

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

/**
 * Age of the person/animal/object that born in given date
 *
 * @return Age of the person/animal/object that born in given date
 */
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

/**
 * Date as string in format yyyy/MM/dd
 */
@SuppressLint("SimpleDateFormat")
val dateFormatter = SimpleDateFormat("yyyy/MM/dd")

/**
 * Date as string in format yyyy/MM/dd
 */
val Calendar.dateString: String get() = dateFormatter.format(this.time)
