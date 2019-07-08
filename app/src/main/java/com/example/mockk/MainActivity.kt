package com.example.mockk

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

interface MainView {
    fun getUserInput(): String
    fun showInput(input: String)
    fun setButtonClickListener(clickListener: () -> Unit)
}

class MainActivity : AppCompatActivity(), MainView {
    val presenter: MainPresenter = MainPresenter(this, MainInteractor(), DefaultContextProvider())

    override fun getUserInput(): String = user_input.text.toString()

    override fun showInput(input: String) {
        text_view.text = input
    }

    override fun setButtonClickListener(clickListener: () -> Unit) {
        user_input_btn.setOnClickListener { clickListener() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        presenter.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
