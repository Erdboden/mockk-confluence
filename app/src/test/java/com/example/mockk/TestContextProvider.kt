package com.example.mockk

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider:ContextProvider {
    override val UI: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}