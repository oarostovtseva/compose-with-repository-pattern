package com.orost.android.words.data.words

import kotlin.random.Random

private val CharPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

val RandomWords: List<String> = with(Random("Words".hashCode())) {
  (0..20).map { nextWord(length = nextInt(3, 20)) }
}

private fun Random.nextWord(length: Int): String =
  String(
    chars = (0..length).map { nextInt(0, CharPool.size) }.map(CharPool::get).toCharArray()
  )

