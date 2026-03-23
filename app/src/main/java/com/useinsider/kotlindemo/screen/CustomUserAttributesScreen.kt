package com.useinsider.kotlindemo.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.useinsider.kotlindemo.component.InsiderGradientButton
import com.useinsider.kotlindemo.component.ParameterRow
import com.useinsider.kotlindemo.ui.theme.InsiderBeige
import com.useinsider.kotlindemo.ui.theme.InsiderDanger
import com.useinsider.kotlindemo.ui.theme.InsiderTextDark
import com.useinsider.kotlindemo.viewmodel.CustomAttributesViewModel

@Composable
fun CustomUserAttributesScreen(
    viewModel: CustomAttributesViewModel,
    onBack: () -> Unit,
    onAttributesSet: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(InsiderBeige)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp)
    ) {
        Spacer(Modifier.height(8.dp))

        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = InsiderTextDark
                )
            }
            Text(
                text = "Set User Attributes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = InsiderTextDark
            )
        }

        Spacer(Modifier.height(16.dp))

        // Attribute rows
        viewModel.attributes.forEachIndexed { index, attr ->
            ParameterRow(
                parameter = attr,
                onTypeChange = { viewModel.updateAttributeType(index, it) },
                onNameChange = { viewModel.updateAttributeName(index, it) },
                onValueChange = { viewModel.updateAttributeValue(index, it) },
                onDelete = { viewModel.removeAttribute(index) },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // + Add Attribute
        TextButton(
            onClick = { viewModel.addAttribute() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "+ Add Attribute",
                color = InsiderDanger,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(16.dp))

        // Set Attributes button
        InsiderGradientButton(
            text = "Set Attributes",
            onClick = {
                viewModel.setAttributes { result ->
                    onAttributesSet(result)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))
    }
}
