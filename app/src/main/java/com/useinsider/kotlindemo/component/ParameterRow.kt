package com.useinsider.kotlindemo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.useinsider.kotlindemo.model.EventParameter
import com.useinsider.kotlindemo.model.ParameterType
import com.useinsider.kotlindemo.ui.theme.InsiderDanger
import com.useinsider.kotlindemo.ui.theme.InsiderInputBg
import com.useinsider.kotlindemo.ui.theme.InsiderInputBorder
import com.useinsider.kotlindemo.ui.theme.InsiderTextDark

@Composable
fun ParameterRow(
    parameter: EventParameter,
    onTypeChange: (ParameterType) -> Unit,
    onNameChange: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Type selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ParameterType.entries.forEach { type ->
                FilterChip(
                    selected = parameter.type == type,
                    onClick = { onTypeChange(type) },
                    label = {
                        Text(
                            text = type.name.lowercase().replaceFirstChar { it.uppercase() },
                            fontSize = 12.sp
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.White,
                        selectedLabelColor = InsiderTextDark
                    ),
                    modifier = Modifier.height(36.dp)
                )
            }
        }

        // Name / Value fields
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedTextField(
                value = parameter.name,
                onValueChange = onNameChange,
                placeholder = { Text("Name", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = InsiderInputBg,
                    unfocusedContainerColor = InsiderInputBg,
                    focusedBorderColor = InsiderInputBorder,
                    unfocusedBorderColor = InsiderInputBorder
                )
            )
            OutlinedTextField(
                value = parameter.value,
                onValueChange = onValueChange,
                placeholder = { Text("Value", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(6.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = InsiderInputBg,
                    unfocusedContainerColor = InsiderInputBg,
                    focusedBorderColor = InsiderInputBorder,
                    unfocusedBorderColor = InsiderInputBorder
                )
            )
        }

        // Delete button
        TextButton(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Delete",
                color = InsiderDanger,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        HorizontalDivider(color = InsiderInputBorder, thickness = 1.dp)
    }
}
