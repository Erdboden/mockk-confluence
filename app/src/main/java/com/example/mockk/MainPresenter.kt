package com.example.mockk

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
        GlobalScope.launch(contextProvider.UI) {
            val input = view.getUserInput()
            try {
                withContext(contextProvider.IO) { interactor.saveInput(input) }
                view.showInput(input)
            } catch (e: Exception) {
                view.showInput("Invalid $input")
            }
        }
    }
}