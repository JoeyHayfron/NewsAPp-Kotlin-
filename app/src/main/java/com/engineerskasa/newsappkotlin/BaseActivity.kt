package com.engineerskasa.newsappkotlin

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

internal const val QUERY_VAL = ""

open class BaseActivity : AppCompatActivity() {
    private val TAG = "BaseActivity"

    internal fun activateToolbar(homeEnabled: Boolean, title: String) {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = title
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
    }
}