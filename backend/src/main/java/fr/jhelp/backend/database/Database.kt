package fr.jhelp.backend.database

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import fr.jhelp.backend.data.Person
import fr.jhelp.tool.providers.provided
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal object Database : DatabaseErrorHandler
{
    private val context: Context by provided<Context>()
    private val database: SQLiteDatabase by lazy {
        val database = this.context.openOrCreateDatabase("Database", Context.MODE_PRIVATE, null, this)
        this.initialize(database)
        database
    }

    fun addOrUpdatePerson(person: Person)
    {
        CoroutineScope(Dispatchers.IO).launch { this@Database.internalAddOrUpdatePerson(person) }
    }

    fun allPerson(collector: (Person) -> Unit, collectFinished: () -> Unit)
    {
        CoroutineScope(Dispatchers.IO).launch { this@Database.internalAllPerson(collector, collectFinished) }
    }

    private fun internalAddOrUpdatePerson(person: Person)
    {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, person.name)
        contentValues.put(COLUMN_BIRTH_DATE, person.birthDate)

        if (person.databaseId < 0L)
        {
            person.databaseId = this.database.insert(TABLE_PERSON, null, contentValues)
        }
        else
        {
            this.database.update(TABLE_PERSON, contentValues, WHERE_ID, arrayOf(person.databaseId.toString()))
        }
    }

    private fun internalAllPerson(collector: (Person) -> Unit, collectFinished: () -> Unit)
    {
        val cursor = this.database.query(
            TABLE_PERSON, PERSON_ALL_COLUMNS,
            null, null,
            null, null, null
                                        )

        while (cursor.moveToNext())
        {
            val id = cursor.getLong(PERSON_ALL_COLUMNS_INDEX_ID)
            val name = cursor.getString(PERSON_ALL_COLUMNS_INDEX_NAME)
            val birthDate = cursor.getCalendar(PERSON_ALL_COLUMNS_INDEX_BIRTH_DATE)
            collector(Person(name, birthDate, id))
        }

        cursor.close()
        collectFinished()
    }

    private fun initialize(database: SQLiteDatabase)
    {
        val currentVersion = this.currentVersion(database)
        this.version(database, currentVersion)
    }

    /*
    String table, String[] columns, String selection,
                String[] selectionArgs, String groupBy, String having,
                String orderBy, [String limit]
     */
    private fun currentVersion(database: SQLiteDatabase): Int
    {
        var version = 0
        database.execSQL(CREATE_VERSION)
        val cursor = database.query(
            TABLE_VERSION,
            arrayOf(COLUMN_VERSION),
            null, null,
            null, null, null
                                   )

        if (cursor.moveToNext())
        {
            version = cursor.getInt(0)
        }

        cursor.close()
        return version
    }

    private fun version(database: SQLiteDatabase, currentVersion: Int)
    {
        if (currentVersion < DATABASE_VERSION)
        {
            when (currentVersion)
            {
                0 -> this.lastVersion(database)
                // 1 -> this.transition_1_2(database)
                // 2 -> this.transition_2_3(database)
                // ...
            }

            if (currentVersion == 0)
            {
                val contentValues = ContentValues()
                contentValues.put(COLUMN_VERSION, DATABASE_VERSION)
                database.insert(TABLE_VERSION, null, contentValues)
            }
            else
            {
                val contentValues = ContentValues()
                contentValues.put(COLUMN_VERSION, DATABASE_VERSION)
                database.update(TABLE_VERSION, contentValues, null, null)
            }
        }
    }

    private fun lastVersion(database: SQLiteDatabase)
    {
        database.execSQL(CREATE_PERSON)
        // TODO : create tables
    }

    override fun onCorruption(sqLiteDatabase: SQLiteDatabase)
    {
        Log.e("DATABASE", "Database corrupted")
    }
}
