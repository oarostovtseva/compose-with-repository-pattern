package com.orost.android.words.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orost.android.words.ui.bars.MainTopBar

@Composable
fun LoadingUi() {
  Scaffold(
      topBar = { MainTopBar() },
      content = { LoadingIndicator() },
  )
}

@Composable
fun LoadingIndicator() {
  Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}
