package com.example.polyclinic.polyclinic.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.appbar.AppBarLayout

abstract class BaseToolbarActivity : AppCompatActivity() {

    protected var appbar: AppBarLayout? = null
    protected var toolbar: Toolbar? = null
    protected var toolbarShadow: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        appbar = findViewById(R.id.appbar)
        toolbar = findViewById(R.id.base_toolbar)
        toolbarShadow = findViewById(R.id.base_toolbar_shadow)

        setSupportActionBar(toolbar)
    }

    @LayoutRes
    open fun getLayoutId(): Int {
        return R.layout.activity_base_toolbar
    }

    open fun isToolbarTitleEnabled():Boolean = true

    open fun setToolbarTitle(title: CharSequence?) {
        if (isToolbarTitleEnabled() && title != null) {
            toolbar!!.title = title
        }
}

    open fun setToolbarTitle(@StringRes resId: Int) {
        setToolbarTitle(getString(resId))
    }
}
