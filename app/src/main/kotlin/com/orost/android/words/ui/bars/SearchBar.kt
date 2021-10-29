package com.orost.android.words.ui.bars

import androidx.activity.compose.BackHandler
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.orost.android.words.ui.icons.BackIcon
import com.orost.android.words.ui.icons.SearchIcon
import com.orost.android.words.ui.text.SearchTextField

@Composable
fun SearchBar(
    search: String?,
    onSearch: (String?) -> Unit,
) {
  when (search) {
    null -> MainTopBar(
        actions = { SearchIcon { onSearch("") } }
    )
    else -> TopAppBar(
        title = {
          SearchTextField(search, onSearch)
        },
        navigationIcon = {
          BackHandler { onSearch(null) }
          BackIcon { onSearch(null) }
        },
        backgroundColor = MaterialTheme.colors.background
    )
  }
}
