package com.orost.android.words.data.words.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalWord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val words: WordDao
}