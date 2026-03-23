package com.useinsider.kotlindemo.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.useinsider.kotlindemo.R
import com.useinsider.kotlindemo.ui.theme.InsiderTextDark

@Composable
fun HeaderBar(
    onTrashClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.insider_logo),
            contentDescription = "Insider Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .height(24.dp)
        )
        IconButton(
            onClick = onTrashClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Clear",
                tint = InsiderTextDark
            )
        }
    }
}
