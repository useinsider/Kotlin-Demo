package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class InputFieldItem(
    val label: String,
    val value: String,
    val onValueChange: (String) -> Unit,
    val onSetClick: () -> Unit
)

@Composable
fun InputFieldGrid(
    fields: List<InputFieldItem>,
    columns: Int = 2,
    modifier: Modifier = Modifier
) {
    val rows = fields.chunked(columns)
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowItems.forEach { item ->
                    TextFieldWithSetButton(
                        label = item.label,
                        value = item.value,
                        onValueChange = item.onValueChange,
                        onSetClick = item.onSetClick,
                        modifier = Modifier.weight(1f)
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
