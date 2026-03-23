package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.useinsider.kotlindemo.model.ButtonItem

@Composable
fun ButtonGrid(
    columns: Int,
    buttons: List<ButtonItem>,
    modifier: Modifier = Modifier
) {
    val rows = buttons.chunked(columns)
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowItems.forEach { item ->
                    InsiderGradientButton(
                        text = item.text,
                        onClick = item.onClick,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    )
                }
                repeat(columns - rowItems.size) {
                    androidx.compose.foundation.layout.Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
