package com.example.notesapp.data.model


class Note {

    var id:Int = 0
    lateinit var text:String
    lateinit var timeStamp:String

    companion object {
        internal const val COLUMN_TEXT = "text_data"
        internal const val COLUMN_ID = "id"
        internal const val TABLE_NAME = "Note"
        internal const val COLUMN_TIMESTAMP = "timestamp"

        var CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TEXT + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")")
    }

}