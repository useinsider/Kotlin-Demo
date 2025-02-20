package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InappsComponent(
    title: String,
    buttonText1: String,
    buttonText2: String,
    onButtonClick1: () -> Unit,
    onButtonClick2: () -> Unit,
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
            onClick = onButtonClick1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = buttonText1, color = Color.White)
        }
        Button(
            onClick = onButtonClick2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = buttonText2, color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
@Preview
fun InappsComponentPreview() {
    InappsComponent(
        title = "Title",
        buttonText1 = "Button1",
        buttonText2 = "Button2",
        onButtonClick1 = {},
        onButtonClick2 = {}
    )
}