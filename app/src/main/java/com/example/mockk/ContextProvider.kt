package com.example.mockk

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    val UI: CoroutineContext
    val IO: CoroutineContext
}

class DefaultContextProvider : ContextProvider {
    override val UI: CoroutineContext = Dispatchers.Main
    override val IO: CoroutineContext = Dispatchers.IO
}