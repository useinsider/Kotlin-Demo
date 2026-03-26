package com.useinsider.kotlindemo.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.useinsider.insider.InsiderAppCard
import com.useinsider.insider.InsiderAppCardButton
import com.useinsider.kotlindemo.component.InsiderGradientButton
import com.useinsider.kotlindemo.ui.theme.Figtree
import com.useinsider.kotlindemo.ui.theme.InsiderBeige
import com.useinsider.kotlindemo.ui.theme.InsiderOrangeStart
import com.useinsider.kotlindemo.ui.theme.InsiderTextDark
import com.useinsider.kotlindemo.ui.theme.InsiderTextGray
import com.useinsider.kotlindemo.viewmodel.AppCardsViewModel

private val ReadBadgeColor = Color(0xFF10B981)
private val UnreadBadgeColor = Color(0xFFFF6B35)
private val ReadBadgeBg = Color(0xFFD1FAE5)
private val UnreadBadgeBg = Color(0xFFFFE5D9)
private val CardBg = Color.White
private val IndicatorInactive = Color(0xFFD4D0C9)

@Composable
fun AppCardsScreen(
    viewModel: AppCardsViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadAppCards()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(InsiderBeige)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp)
        ) {
            IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = InsiderTextDark
                )
            }
            Text(
                text = "App Cards",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Figtree,
                color = InsiderTextDark
            )
        }

        when {
            viewModel.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = InsiderOrangeStart)
                }
            }

            viewModel.errorMessage != null -> {
                EmptyState(message = viewModel.errorMessage!!)
            }

            viewModel.appCards.isEmpty() -> {
                EmptyState(message = "No app cards available")
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(viewModel.appCards) { index, card ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(initialAlpha = 0f)
                        ) {
                            AppCardItem(
                                appCard = card,
                                onMarkReadToggle = {
                                    if (card.isRead) {
                                        viewModel.markAsUnread(card.id)
                                    } else {
                                        viewModel.markAsRead(card.id)
                                    }
                                }
                            )
                        }
                    }
                    item { Spacer(Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                fontSize = 16.sp,
                fontFamily = Figtree,
                color = InsiderTextGray
            )
        }
    }
}

@Composable
private fun AppCardItem(
    appCard: InsiderAppCard,
    onMarkReadToggle: () -> Unit
) {
    var showDetailsDialog by remember { mutableStateOf(false) }

    LaunchedEffect(appCard.id) {
        appCard.view()
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { appCard.click() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: ID + read status badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val shortId = if (appCard.id.length > 16) appCard.id.take(16) + "..." else appCard.id
                Text(
                    text = "ID: $shortId",
                    fontSize = 11.sp,
                    fontFamily = Figtree,
                    color = InsiderTextGray
                )
                ReadStatusBadge(isRead = appCard.isRead)
            }

            Spacer(Modifier.height(8.dp))

            // Image carousel
            val images = appCard.images
            if (!images.isNullOrEmpty()) {
                ImageCarousel(imageUrls = images.map { it.url })
                Spacer(Modifier.height(10.dp))
            }

            // Title
            val content = appCard.content
            if (content != null) {
                val title = content.title
                if (!title.isNullOrEmpty()) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Figtree,
                        color = InsiderTextDark
                    )
                    Spacer(Modifier.height(4.dp))
                }

                // Description
                val description = content.description
                if (!description.isNullOrEmpty()) {
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        fontFamily = Figtree,
                        color = InsiderTextGray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }

            // Dynamic buttons from card data
            val buttons = appCard.buttons
            if (!buttons.isNullOrEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    buttons.forEach { button ->
                        AppCardDynamicButton(button = button)
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            // Action buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InsiderGradientButton(
                    text = if (appCard.isRead) "Mark Unread" else "Mark Read",
                    onClick = onMarkReadToggle,
                    modifier = Modifier.weight(1f)
                )
                InsiderGradientButton(
                    text = "View Details",
                    onClick = { showDetailsDialog = true },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    if (showDetailsDialog) {
        AppCardDetailsDialog(
            appCard = appCard,
            onDismiss = { showDetailsDialog = false }
        )
    }
}

@Composable
private fun ReadStatusBadge(isRead: Boolean) {
    val bgColor = if (isRead) ReadBadgeBg else UnreadBadgeBg
    val dotColor = if (isRead) ReadBadgeColor else UnreadBadgeColor
    val label = if (isRead) "Read" else "Unread"

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(dotColor)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontFamily = Figtree,
            fontWeight = FontWeight.Medium,
            color = dotColor
        )
    }
}

@Composable
private fun ImageCarousel(imageUrls: List<String>) {
    if (imageUrls.size == 1) {
        AsyncImage(
            model = imageUrls[0],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp))
        )
    } else {
        val pagerState = rememberPagerState(pageCount = { imageUrls.size })

        Column {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(8.dp))
            ) { page ->
                AsyncImage(
                    model = imageUrls[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.height(8.dp))

            // Page indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                imageUrls.indices.forEach { index ->
                    val color = if (index == pagerState.currentPage) InsiderOrangeStart else IndicatorInactive
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .padding(horizontal = 2.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    if (index < imageUrls.lastIndex) Spacer(Modifier.width(4.dp))
                }
            }
        }
    }
}

@Composable
private fun AppCardDynamicButton(button: InsiderAppCardButton) {
    InsiderGradientButton(
        text = button.text,
        onClick = { button.click() },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun AppCardDetailsDialog(
    appCard: InsiderAppCard,
    onDismiss: () -> Unit
) {
    val details = buildString {
        appendLine("ID: ${appCard.id}")
        appendLine("Type: ${appCard.type}")
        appendLine("Status: ${if (appCard.isRead) "Read" else "Unread"}")
        appendLine()

        val content = appCard.content
        if (content != null) {
            if (!content.title.isNullOrEmpty()) appendLine("Title: ${content.title}")
            if (!content.description.isNullOrEmpty()) appendLine("Description: ${content.description}")
            appendLine()
        }

        val images = appCard.images
        if (!images.isNullOrEmpty()) {
            appendLine("Images:")
            images.forEachIndexed { i, img -> appendLine("  ${i + 1}. ${img.url}") }
            appendLine()
        }

        val buttons = appCard.buttons
        if (!buttons.isNullOrEmpty()) {
            appendLine("Buttons:")
            buttons.forEachIndexed { i, btn ->
                appendLine("  ${i + 1}. ${btn.text}")
                appendLine("     ID: ${btn.id}")
                btn.action?.let { appendLine("     Action: ${it.type}") }
            }
            appendLine()
        }

        appCard.action?.let { action ->
            appendLine("Action:")
            appendLine("  Details: ${action.toJSONObject()}")
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Card Details",
                fontWeight = FontWeight.Bold,
                fontFamily = Figtree
            )
        },
        text = {
            Text(
                text = details,
                fontSize = 13.sp,
                fontFamily = Figtree,
                color = InsiderTextDark
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK", color = InsiderOrangeStart)
            }
        }
    )
}
