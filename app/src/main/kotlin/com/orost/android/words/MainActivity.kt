package com.orost.android.words

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.orost.android.words.ui.LoadingUi
import com.orost.android.words.ui.WordListUi
import com.orost.android.words.ui.theme.WordsTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.load()
        setContent {
            val words by viewModel.words.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val search by viewModel.search.collectAsState()
            WordsTheme {
                when {
                    isLoading -> LoadingUi()
                    else -> WordListUi(
                        words,
                        search = search,
                        onSearch = viewModel::search
                    )
                }
            }
        }
    }

}
