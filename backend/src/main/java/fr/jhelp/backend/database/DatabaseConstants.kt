package fr.jhelp.backend.database

internal const val DATABASE_VERSION = 1

//    | Version |
//    +---------+
//    | Version |
//    | INTEGER |
internal const val TABLE_VERSION = "Version"
internal const val COLUMN_VERSION = "Version"
internal const val CREATE_VERSION = "CREATE TABLE IF NOT EXISTS $TABLE_VERSION ($COLUMN_VERSION INTEGER)"

//    |           Person             |
//    +------------------------------+
//    |   id    |  Name  | BirthDate |
//    | INTEGER |  TEXT  |  INTEGER  |
internal const val TABLE_PERSON = "Person"
internal const val COLUMN_ID = "id"
internal const val COLUMN_NAME = "Name"
internal const val COLUMN_BIRTH_DATE = "BirthDate"
internal const val CREATE_PERSON = "CREATE TABLE IF NOT EXISTS $TABLE_PERSON ($COLUMN_ID INTEGER PRIMARY KEY ASC AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_BIRTH_DATE INTEGER)"
internal val PERSON_ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_BIRTH_DATE)
internal const val PERSON_ALL_COLUMNS_INDEX_ID = 0
internal const val PERSON_ALL_COLUMNS_INDEX_NAME = 1
internal const val PERSON_ALL_COLUMNS_INDEX_BIRTH_DATE = 2
internal const val WHERE_ID = "$COLUMN_ID=?"