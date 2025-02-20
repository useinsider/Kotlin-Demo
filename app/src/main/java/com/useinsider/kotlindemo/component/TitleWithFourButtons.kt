package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            maxLines = 2,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun TitleWithFourButtons(
    title: String,
    firstButtonText: String,
    secondButtonText: String,
    thirdButtonText: String,
    fourthButtonText: String,
    firstButtonClick: () -> Unit,
    secondButtonClick: () -> Unit,
    thirdButtonClick: () -> Unit,
    fourthButtonClick: () -> Unit,
) {
    Column {
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomButton(thirdButtonText, thirdButtonClick, modifier = Modifier.weight(1f))
            CustomButton(fourthButtonText, fourthButtonClick, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
@Preview
fun TitleWithFourButtonsPreview() {
    TitleWithFourButtons(
        title = "Title",
        firstButtonText = "First Button",
        secondButtonText = "Second Button",
        thirdButtonText = "Third Button",
        fourthButtonText = "Fourth Button",
        firstButtonClick = {},
        secondButtonClick = {},
        thirdButtonClick = {},
        fourthButtonClick = {}
    )
}