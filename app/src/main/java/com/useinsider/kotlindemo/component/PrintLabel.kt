package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.useinsider.kotlindemo.ui.theme.InsiderTextGray

@Composable
fun PrintLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            color = InsiderTextGray,
            fontSize = 12.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}
