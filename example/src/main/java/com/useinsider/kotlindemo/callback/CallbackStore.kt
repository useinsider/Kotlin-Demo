package com.useinsider.kotlindemo.callback

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public object CallbackStore {
    private val _latestCallback = MutableStateFlow("")
    public val latestCallback: StateFlow<String> = _latestCallback

    public fun update(message: String): Unit {
        _latestCallback.value = message
    }
}
