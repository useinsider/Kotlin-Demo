package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.useinsider.kotlindemo.action.InsiderActions
import com.useinsider.kotlindemo.model.EventParameter
import com.useinsider.kotlindemo.model.ParameterType

class CustomAttributesViewModel : ViewModel() {

    val attributes = mutableStateListOf(EventParameter())

    fun addAttribute() {
        attributes.add(EventParameter())
    }

    fun removeAttribute(index: Int) {
        if (index in attributes.indices) {
            attributes.removeAt(index)
        }
    }

    fun updateAttributeType(index: Int, type: ParameterType) {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(type = type)
        }
    }

    fun updateAttributeName(index: Int, name: String) {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(name = name)
        }
    }

    fun updateAttributeValue(index: Int, value: String) {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(value = value)
        }
    }

    fun setAttributes(callback: (String) -> Unit) {
        InsiderActions.setCustomAttributes(attributes.toList(), callback)
    }
}
