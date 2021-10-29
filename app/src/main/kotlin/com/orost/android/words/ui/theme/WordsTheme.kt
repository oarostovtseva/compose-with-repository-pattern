package com.orost.android.words.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orost.android.words.R

@Composable
fun WordsTheme(content: @Composable () -> Unit) {
  val systemUiController = rememberSystemUiController()
  val color = WordsTheme.colors.primary
  SideEffect {
    systemUiController.setSystemBarsColor(color = color)
  }
  MaterialTheme(
      colors = WordsTheme.colors,
      typography = WordsTheme.typography,
      shapes = WordsTheme.shapes,
      content = content
  )
}

object WordsTheme {

  val colors: Colors
    @Composable get() = when {
      isSystemInDarkTheme() -> DarkColorPalette
      else -> LightColorPalette
    }
  val typography: Typography = wordsTypography
  val shapes: Shapes = wordsShapes
}

object WordsColors {

  val salem @Composable get() = colorResource(id = R.color.salem)
  val rwGrey @Composable get() = colorResource(id = R.color.rw_grey)
}

private val wordsShapes
  get() = Shapes(
      small = RoundedCornerShape(4.dp),
      medium = RoundedCornerShape(4.dp),
      large = RoundedCornerShape(0.dp)
  )

private val wordsTypography
  get() = Typography(
      body1 = TextStyle(
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
      )
  )

private val DarkColorPalette
  @Composable get() = darkColors(
      primary = WordsColors.rwGrey,
  )

private val LightColorPalette
  @Composable get() = lightColors(
      primary = WordsColors.rwGrey,
  )


