package com.useinsider.kotlindemo.model

public data class EventParameter(
    val type: ParameterType = ParameterType.STRING,
    val name: String = "",
    val value: String = ""
)

public enum class ParameterType {
    STRING, INTEGER, DOUBLE, BOOLEAN
}
