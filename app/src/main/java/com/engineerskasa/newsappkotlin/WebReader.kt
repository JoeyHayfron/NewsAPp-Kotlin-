package com.engineerskasa.newsappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_web_reader.*

class WebReader : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView  = WebView(this)
        setContentView(webView)
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(intent?.getStringExtra("SITE"))

    }
}