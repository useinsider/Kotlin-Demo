package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.useinsider.kotlindemo.action.InsiderActions
import com.useinsider.kotlindemo.model.EventParameter
import com.useinsider.kotlindemo.model.ParameterType

class CustomEventViewModel : ViewModel() {

    var eventName by mutableStateOf("")
    val parameters = mutableStateListOf(EventParameter())

    fun addParameter() {
        parameters.add(EventParameter())
    }

    fun removeParameter(index: Int) {
        if (index in parameters.indices) {
            parameters.removeAt(index)
        }
    }

    fun updateParameterType(index: Int, type: ParameterType) {
        if (index in parameters.indices) {
            parameters[index] = parameters[index].copy(type = type)
        }
    }

    fun updateParameterName(index: Int, name: String) {
        if (index in parameters.indices) {
            parameters[index] = parameters[index].copy(name = name)
        }
    }

    fun updateParameterValue(index: Int, value: String) {
        if (index in parameters.indices) {
            parameters[index] = parameters[index].copy(value = value)
        }
    }

    fun sendEvent(callback: (String) -> Unit) {
        InsiderActions.tagCustomEvent(eventName, parameters.toList(), callback)
    }
}
