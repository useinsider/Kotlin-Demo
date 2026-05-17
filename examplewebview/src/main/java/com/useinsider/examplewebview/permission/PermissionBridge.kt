package com.useinsider.examplewebview.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.core.content.ContextCompat

public class PermissionBridge(
    private val activity: Activity,
    private val webView: WebView,
    private val requestPush: ((Boolean) -> Unit) -> Unit,
    private val requestLocation: ((Boolean) -> Unit) -> Unit
) {

    @JavascriptInterface
    public fun request(kind: String, callbackId: String) {
        activity.runOnUiThread {
            val responder: (Boolean) -> Unit = {
                granted -> respond(callbackId, granted)
            }
            when (kind) {
                "push" -> {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) { responder(true); return@runOnUiThread }
                    val permission = Manifest.permission.POST_NOTIFICATIONS
                    if (isGranted(permission)) responder(true) else requestPush(responder)
                }
                "location" -> {
                    if (isGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        responder(true)
                    } else {
                        requestLocation(responder)
                    }
                }
                else -> responder(false)
            }
        }
    }

    private fun isGranted(name: String): Boolean =
        ContextCompat.checkSelfPermission(activity, name) == PackageManager.PERMISSION_GRANTED

    private fun respond(callbackId: String, granted: Boolean) {
        val callback = "window._permissionResolve && window._permissionResolve('$callbackId', $granted);"
        webView.post {
            webView.evaluateJavascript(callback, null)
        }
    }
}
