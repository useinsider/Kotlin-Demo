package com.useinsider.kotlindemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TitleWithSingleButton(
    title: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold, color = Color.Black
            ), modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = buttonText, color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
@Preview
fun TitleWithSingleButtonPreview() {
    TitleWithSingleButton(
        title = "Title",
        buttonText = "Button",
        onButtonClick = {}
    )
}