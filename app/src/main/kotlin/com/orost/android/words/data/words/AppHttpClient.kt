package com.orost.android.words.data.words

import io.ktor.client.HttpClient

val AppHttpClient: HttpClient by lazy {
    HttpClient()
}