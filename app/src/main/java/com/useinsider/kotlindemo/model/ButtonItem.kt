package com.useinsider.kotlindemo.model

data class ButtonItem(
    val text: String,
    val onClick: () -> Unit
)
