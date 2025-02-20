package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun TitleWithTwoButtons(
    title: String,
    firstButtonText: String,
    secondButtonText: String,
    firstButtonClick: () -> Unit,
    secondButtonClick: () -> Unit,
) {
    Column() {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold, color = Color.Black
            ), modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(firstButtonText, firstButtonClick, modifier = Modifier.weight(1f))
            CustomButton(secondButtonText, secondButtonClick, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
@Preview
fun TitleWithTwoButtonsPreview() {
    TitleWithTwoButtons(
        title = "Title",
        firstButtonText = "First Button",
        secondButtonText = "Second Button",
        firstButtonClick = {},
        secondButtonClick = {}
    )
}