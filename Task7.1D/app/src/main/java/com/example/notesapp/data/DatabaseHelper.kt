package com.example.notesapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.notesapp.data.model.Note

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private var instance: DatabaseHelper? = null

        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "notes_app"

        fun getInstance(context: Context): DatabaseHelper? {
            if (instance == null) {
                synchronized(DatabaseHelper::class.java) {
                    if (instance == null) {
                        instance = DatabaseHelper(context)
                    }
                }
            }
            return instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Note.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertNote(text: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Note.COLUMN_TEXT, text)
        db.insert(Note.TABLE_NAME, null, values)
    }

    fun getNotes(): List<Note> {
        val db = this.readableDatabase
        val noteArrayList = java.util.ArrayList<Note>()
        val query = "SELECT * FROM " + Note.TABLE_NAME
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val note = Note()
                note.id = cursor.getInt(cursor.getColumnIndexOrThrow(Note.COLUMN_ID))
                note.text = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_TEXT))
                note.timeStamp =
                    cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_TIMESTAMP))
                noteArrayList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return noteArrayList
    }

    fun updateNote(text: String, id: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Note.COLUMN_TEXT, text)
        db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + "= " + id, null);
    }

    fun deleteNote(id: String) {
        val db = this.writableDatabase
        db.execSQL("Delete from " + Note.TABLE_NAME + " where " + Note.COLUMN_ID + " ='$id'")
    }

}