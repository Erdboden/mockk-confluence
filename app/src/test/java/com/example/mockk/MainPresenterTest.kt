package com.example.mockk

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainPresenterTest {
    val view = mockk<MainView>(relaxed = true)
    val interactor = mockk<MainInteractor>(relaxed = true)
    val presenter = MainPresenter(view, interactor, TestContextProvider())

    @Before
    fun setUp() {
        every {
            view.setButtonClickListener(captureLambda())
        } answers {
            lambda<() -> Unit>().invoke()
        }
    }

    @Test
    fun `sets button click listener on create`() {
        presenter.onCreate()

        verify { view.setButtonClickListener(captureLambda()) }
    }

    @Test
    fun `receives user input on button click`() {
        presenter.onCreate()

        verify { view.getUserInput() }
    }

    @Test
    fun `saves user input on button click`() {
        presenter.onCreate()

        coVerify { interactor.saveInput("") }
    }

    @Test
    fun `shows user input on button click`() {
        presenter.onCreate()

        verify { view.showInput("") }
    }

    @Test
    fun `throws exception on save`() {
        val input = "bad input"
        every { view.getUserInput() } returns input
        coEvery { interactor.saveInput(input) } throws Exception()

        presenter.onCreate()

        verify { view.showInput("Invalid $input") }
    }
}