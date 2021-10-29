package com.orost.android.words.data.words.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.orost.android.words.data.words.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordStore(database: AppDatabase) {

    private val words = database.words

    fun all(): Flow<PagingData<Word>> = pagingWord { words.queryAll() }

    fun all(term: String): Flow<PagingData<Word>> =
        pagingWord { words.searchAll(term) }

    suspend fun save(words: List<Word>) {
        this.words.insert(words.map { it.toLocal() })
    }

    suspend fun isEmpty(): Boolean = words.count() == 0L

    private fun pagingWord(
        block: () -> PagingSource<Int, LocalWord>,
    ): Flow<PagingData<Word>> =
        Pager(PagingConfig(pageSize = 20)) { block() }.flow
            .map { page -> page.map { localWord -> localWord.fromLocal() } }
}

private fun Word.toLocal() = LocalWord(
    value = value,
)

private fun LocalWord.fromLocal() = Word(
    value = value,
)