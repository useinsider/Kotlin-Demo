package com.useinsider.examplewebview

import android.Manifest
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.useinsider.examplewebview.permission.PermissionBridge
import com.useinsider.insiderwebview.InsiderWebView

public class MainActivity : ComponentActivity() {

    private var pendingPermissionCallback: ((Boolean) -> Unit)? = null

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { grants ->
        val granted = grants.values.all { it }
        pendingPermissionCallback?.invoke(granted)
        pendingPermissionCallback = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.web_view)
        InsiderWebView.setupWebViewSDK(webView)

        val bridge = PermissionBridge(
            activity = this,
            webView = webView,
            requestPush = { onResult ->
                pendingPermissionCallback = onResult
                permissionLauncher.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
            },
            requestLocation = { onResult ->
                pendingPermissionCallback = onResult
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        )
        webView.addJavascriptInterface(bridge, "Permissions")

        // Locally (via file)
        webView.loadUrl("file:///android_asset/index.html")

        // Remotely (via URL)
        // webView.loadUrl("http://localhost:8080/index.html")
    }
}
