package com.example.polyclinic.polyclinic.android

import android.os.Bundle
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

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setBackgroundDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_home_button
            )
        )
    }
}