package com.orost.android.words

import android.app.Application
import androidx.room.Room
import com.orost.android.words.data.words.WordRepository
import com.orost.android.words.data.words.local.AppDatabase

class WordsApp : Application() {
    private val database by lazy {
        Room.databaseBuilder(
            this, AppDatabase::class.java,
            "database.db"
        ).build()
    }
    val wordRepository by lazy { WordRepository(database) }
}