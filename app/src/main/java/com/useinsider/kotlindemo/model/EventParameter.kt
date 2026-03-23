package com.useinsider.kotlindemo.model

data class EventParameter(
    val type: ParameterType = ParameterType.STRING,
    val name: String = "",
    val value: String = ""
)

enum class ParameterType {
    STRING, INTEGER, DOUBLE, BOOLEAN
}
