package com.example.polyclinic.polyclinic.android

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.content.res.AppCompatResources

abstract class HomeButtonToolbarActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHomeButton()
    }

    private fun setHomeButton() {
        if (supportActionBar == null) {
            return
        }

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_HOME_AS_UP
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_home_button
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}