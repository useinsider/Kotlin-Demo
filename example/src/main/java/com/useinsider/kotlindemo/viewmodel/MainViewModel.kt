package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.useinsider.kotlindemo.callback.CallbackStore
import kotlinx.coroutines.launch

public class MainViewModel : ViewModel() {

    public var printLabelText: String by mutableStateOf("")
        private set

    // Reinit
    public var reinitName: String by mutableStateOf("")

    // User Attributes
    public var userName: String by mutableStateOf("")
    public var userSurname: String by mutableStateOf("")
    public var userEmail: String by mutableStateOf("")
    public var userPhone: String by mutableStateOf("")
    public var userAge: String by mutableStateOf("")
    public var userGender: String by mutableStateOf("")
    public var userBirthday: String by mutableStateOf("")
    public var userLanguage: String by mutableStateOf("")
    public var userLocale: String by mutableStateOf("")
    public var userFacebookID: String by mutableStateOf("")
    public var userTwitterID: String by mutableStateOf("")

    // Content Optimizer
    public var contentString: String by mutableStateOf("")
    public var contentStringCache: String by mutableStateOf("")
    public var contentBool: String by mutableStateOf("")
    public var contentBoolCache: String by mutableStateOf("")
    public var contentInt: String by mutableStateOf("")
    public var contentIntCache: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            CallbackStore.latestCallback.collect { message ->
                if (message.isNotEmpty()) {
                    printLabelText = message
                }
            }
        }
    }

    public fun updatePrintLabel(text: String): Unit {
        printLabelText = text
    }

    public fun clearPrintLabel(): Unit {
        printLabelText = ""
    }
}
