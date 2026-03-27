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

class AppCardsViewModel : ViewModel() {

    var appCards by mutableStateOf<List<InsiderAppCard>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadAppCards() {
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

    fun markAsRead(appCardId: String) {
        Insider.Instance.appCards().markAsRead(arrayOf(appCardId)) { error ->
            if (error == null) {
                loadAppCards()
            }
        }
    }

    fun markAsUnread(appCardId: String) {
        Insider.Instance.appCards().markAsUnread(arrayOf(appCardId)) { error ->
            if (error == null) {
                loadAppCards()
            }
        }
    }
}
