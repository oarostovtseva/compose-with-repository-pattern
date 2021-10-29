package com.orost.android.words.data.words.remote

import com.orost.android.words.data.words.AppHttpClient
import com.orost.android.words.data.words.Word
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordSource(private val client: HttpClient = AppHttpClient) {                           // 2
    suspend fun load(): List<Word> = withContext(Dispatchers.IO) {
        client.getRemoteWords()
            .lineSequence()
            .map { Word(it) }
            .toList()
    }
}

private suspend fun HttpClient.getRemoteWords(): String =
    get("https://pablisco.com/define/words")
