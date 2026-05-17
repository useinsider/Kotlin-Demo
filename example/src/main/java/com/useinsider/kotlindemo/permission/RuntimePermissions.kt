package com.useinsider.kotlindemo.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

public class RuntimePermissionRequester internal constructor(
    private val context: Context,
    private val launch: (Array<String>) -> Unit,
    private val setPending: (((Boolean) -> Unit)?) -> Unit
) {
    public fun withPushPermission(onResult: (Boolean) -> Unit) {
        // POST_NOTIFICATIONS is only a runtime permission from Android 13 (API 33).
        // On older versions the notification permission is granted at install time.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            onResult(true); return
        }
        val perm = Manifest.permission.POST_NOTIFICATIONS
        if (isGranted(perm)) { onResult(true); return }
        setPending(onResult)
        launch(arrayOf(perm))
    }

    public fun withLocationPermission(onResult: (Boolean) -> Unit) {
        val fine = Manifest.permission.ACCESS_FINE_LOCATION
        val coarse = Manifest.permission.ACCESS_COARSE_LOCATION
        if (isGranted(fine) && isGranted(coarse)) { onResult(true); return }
        setPending(onResult)
        launch(arrayOf(fine, coarse))
    }

    private fun isGranted(name: String): Boolean =
        ContextCompat.checkSelfPermission(context, name) == PackageManager.PERMISSION_GRANTED
}

@Composable
public fun rememberRuntimePermissionRequester(): RuntimePermissionRequester {
    val context = LocalContext.current
    var pendingCallback by remember { mutableStateOf<((Boolean) -> Unit)?>(null) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { grants ->
        val granted = grants.values.all { it }
        pendingCallback?.invoke(granted)
        pendingCallback = null
    }
    return remember(context, launcher) {
        RuntimePermissionRequester(
            context = context,
            launch = { perms -> launcher.launch(perms) },
            setPending = { cb -> pendingCallback = cb }
        )
    }
}
