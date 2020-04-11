package com.example.polyclinic.polyclinic.android

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

abstract class BaseToolbarActivity : BaseActivity() {

    private var appbar: AppBarLayout? = null
    private var toolbar: Toolbar? = null
    private var toolbarShadow: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appbar = findViewById(R.id.appbar)
        toolbar = findViewById(R.id.base_toolbar)
        toolbarShadow = findViewById(R.id.base_toolbar_shadow)

        setSupportActionBar(toolbar)
    }

    @LayoutRes
    override fun getLayoutId(): Int {
        return R.layout.activity_base_toolbar
    }

    open fun isToolbarTitleEnabled(): Boolean = true

    open fun setToolbarTitle(title: CharSequence?) {
        if (isToolbarTitleEnabled() && title != null) {
            toolbar?.title = title
        }
    }

    open fun setToolbarTitle(@StringRes resId: Int) {
        setToolbarTitle(getString(resId))
    }

    open fun getSupportToolbar(): Toolbar? {
        return toolbar
    }
}
