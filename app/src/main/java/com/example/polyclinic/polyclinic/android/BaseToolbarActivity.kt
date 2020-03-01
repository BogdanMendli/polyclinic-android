package com.example.polyclinic.polyclinic.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes

abstract class BaseToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    @LayoutRes
    open fun getLayoutId(): Int {
        return R.layout.activity_base_toolbar
    }
}
