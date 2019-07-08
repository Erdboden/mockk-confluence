package com.example.mockk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    val view: MainView,
    val interactor: MainInteractor,
   val contextProvider: ContextProvider
) {

    fun onCreate() {
        view.setButtonClickListener { handleInput() }
    }

    private fun handleInput() {
        GlobalScope.launch {
            val input = view.getUserInput()
            withContext(contextProvider.IO) { interactor.saveInput(input) }
            withContext(contextProvider.UI) { view.showInput(input) }
        }
    }
}