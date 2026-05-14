package com.useinsider.examplewebview

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import com.useinsider.insiderwebview.InsiderWebView

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.web_view)
        InsiderWebView.setupWebViewSDK(webView)

        // Locally (via file)
        webView.loadUrl("file:///android_asset/index.html")

        // Remotely (via URL)
        // webView.loadUrl("http://localhost:8080/index.html")
    }
}
