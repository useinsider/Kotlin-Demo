package com.useinsider.examplewebview


import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.useinsider.insider.Insider

public class InsiderFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data["source"] == "Insider") {
            Insider.Instance.handleFCMNotification(applicationContext, remoteMessage)
            return
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
