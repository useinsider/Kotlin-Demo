package com.useinsider.kotlindemo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.useinsider.insider.AppCardsCampaignsCallback
import com.useinsider.insider.AppCardsException
import com.useinsider.insider.Insider
import com.useinsider.insider.InsiderAppCard
import com.useinsider.insider.InsiderAppCardCampaignsResponse

public class AppCardsViewModel : ViewModel() {

    public var appCards: List<InsiderAppCard> by mutableStateOf<List<InsiderAppCard>>(emptyList())
        private set

    public var isLoading: Boolean by mutableStateOf(false)
        private set

    public var errorMessage: String? by mutableStateOf<String?>(null)
        private set

    public fun loadAppCards(): Unit {
        isLoading = true
        errorMessage = null

        Insider.Instance.appCards().getCampaigns(object : AppCardsCampaignsCallback {
            override fun onComplete(
                response: InsiderAppCardCampaignsResponse?,
                error: AppCardsException?
            ) {
                isLoading = false
                if (error != null) {
                    errorMessage = "Error loading cards: ${error.message}"
                } else {
                    appCards = response?.appCards.orEmpty()
                }
            }
        })
    }

    public fun markAsRead(appCardId: String): Unit {
        Insider.Instance.appCards().markAsRead(arrayOf(appCardId)) { error ->
            if (error == null) {
                loadAppCards()
            }
        }
    }

    public fun markAsUnread(appCardId: String): Unit {
        Insider.Instance.appCards().markAsUnread(arrayOf(appCardId)) { error ->
            if (error == null) {
                loadAppCards()
            }
        }
    }
}
