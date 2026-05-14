package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.useinsider.kotlindemo.action.InsiderActions
import com.useinsider.kotlindemo.model.EventParameter
import com.useinsider.kotlindemo.model.ParameterType

public class CustomAttributesViewModel : ViewModel() {

    public val attributes: SnapshotStateList<EventParameter> = mutableStateListOf(EventParameter())

    public fun addAttribute(): Unit {
        attributes.add(EventParameter())
    }

    public fun removeAttribute(index: Int): Unit {
        if (index in attributes.indices) {
            attributes.removeAt(index)
        }
    }

    public fun updateAttributeType(index: Int, type: ParameterType): Unit {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(type = type)
        }
    }

    public fun updateAttributeName(index: Int, name: String): Unit {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(name = name)
        }
    }

    public fun updateAttributeValue(index: Int, value: String): Unit {
        if (index in attributes.indices) {
            attributes[index] = attributes[index].copy(value = value)
        }
    }

    public fun setAttributes(callback: (String) -> Unit): Unit {
        InsiderActions.setCustomAttributes(attributes.toList(), callback)
    }
}
