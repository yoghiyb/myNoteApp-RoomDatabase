package com.yoghi.mynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var instatnce: NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instatnce ?: synchronized(LOCK) {
            instatnce ?: buildDatabase(context).also {
                instatnce = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notedatabase"
        ).build()
    }

}