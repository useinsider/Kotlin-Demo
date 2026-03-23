package com.useinsider.kotlindemo.callback

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CallbackStore {
    private val _latestCallback = MutableStateFlow("")
    val latestCallback: StateFlow<String> = _latestCallback

    fun update(message: String) {
        _latestCallback.value = message
    }
}
