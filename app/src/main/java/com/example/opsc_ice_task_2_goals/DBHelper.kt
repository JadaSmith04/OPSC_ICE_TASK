package com.example.opsc_ice_task_2_goals

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "GoalsDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_GOALS = "Goals"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createGoalsTable = ("CREATE TABLE $TABLE_GOALS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_DESCRIPTION TEXT)")
        db?.execSQL(createGoalsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_GOALS")
        onCreate(db)
    }

    // Method to add a new goal to the database
    fun addGoal(title: String, description: String) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_DESCRIPTION, description)

        val db = this.writableDatabase
        db.insert(TABLE_GOALS, null, values)
        db.close()
    }

    fun getGoalIdByTitle(title: String): Int {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_GOALS,
            arrayOf(COLUMN_ID),
            "$COLUMN_TITLE = ?",
            arrayOf(title),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            cursor.close()
            id
        } else {
            cursor.close()
            -1
        }
    }

    fun deleteGoal(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_GOALS, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun getAllGoals(): List<MainActivity.Goal> {
        val goalList = mutableListOf<MainActivity.Goal>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_GOALS", null)

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val goal = MainActivity.Goal(title, description)
                goalList.add(goal)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return goalList
    }
}
