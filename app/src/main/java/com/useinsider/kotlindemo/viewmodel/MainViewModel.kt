package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.useinsider.kotlindemo.callback.CallbackStore
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var printLabelText by mutableStateOf("")
        private set

    // Reinit
    var reinitName by mutableStateOf("")

    // User Attributes
    var userName by mutableStateOf("")
    var userSurname by mutableStateOf("")
    var userEmail by mutableStateOf("")
    var userPhone by mutableStateOf("")
    var userAge by mutableStateOf("")
    var userGender by mutableStateOf("")
    var userBirthday by mutableStateOf("")
    var userLanguage by mutableStateOf("")
    var userLocale by mutableStateOf("")
    var userFacebookID by mutableStateOf("")
    var userTwitterID by mutableStateOf("")

    // Content Optimizer
    var contentString by mutableStateOf("")
    var contentStringCache by mutableStateOf("")
    var contentBool by mutableStateOf("")
    var contentBoolCache by mutableStateOf("")
    var contentInt by mutableStateOf("")
    var contentIntCache by mutableStateOf("")

    init {
        viewModelScope.launch {
            CallbackStore.latestCallback.collect { message ->
                if (message.isNotEmpty()) {
                    printLabelText = message
                }
            }
        }
    }

    fun updatePrintLabel(text: String) {
        printLabelText = text
    }

    fun clearPrintLabel() {
        printLabelText = ""
    }
}
