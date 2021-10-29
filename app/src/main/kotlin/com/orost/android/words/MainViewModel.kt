package com.orost.android.words

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.orost.android.words.data.words.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository = getApplication<WordsApp>().wordRepository

    private val _words = MutableStateFlow(emptyFlow<PagingData<Word>>())
    private val _search = MutableStateFlow(null as String?)
    val search: StateFlow<String?> = _search

    @OptIn(ExperimentalCoroutinesApi::class)
    val words: StateFlow<Flow<PagingData<Word>>> =
        search
            .flatMapLatest { search -> words(search) }
            .stateInViewModel(initialValue = emptyFlow())

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val allWords = MutableStateFlow(emptyFlow<PagingData<Word>>())
    private val searchWords = MutableStateFlow(emptyFlow<PagingData<Word>>())

    fun load() = effect {
        _isLoading.value = true
        allWords.value = wordRepository.allWords()
        _isLoading.value = false
    }

    fun search(term: String?) = effect {
        _search.value = term
        if (term != null) {
            searchWords.value = wordRepository.allWords(term)
        }
    }

    private fun words(search: String?) = when {
        search.isNullOrEmpty() -> allWords
        else -> searchWords
    }

    private fun <T> Flow<T>.stateInViewModel(initialValue: T): StateFlow<T> =
        stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )


    private fun effect(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) { block() }
    }
}
