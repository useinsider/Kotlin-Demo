package com.useinsider.kotlindemo

import android.app.Application
import com.useinsider.insider.Insider
import com.useinsider.insider.InsiderCallback
import com.useinsider.insider.InsiderCallbackType
import org.json.JSONObject
import timber.log.Timber

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO: Please change with your partner name.
        // Make sure that all the letters are lowercase.
        Insider.Instance.init(this, "your_partner_name")

        Insider.Instance.registerInsiderCallback { data, callbackType ->
            when (callbackType) {
                InsiderCallbackType.NOTIFICATION_OPEN ->
                    Timber.tag("[INSIDER]").d("[NOTIFICATION_OPEN]: $data")

                InsiderCallbackType.INAPP_SEEN ->
                    Timber.tag("[INSIDER]").d("[INAPP_BUTTON_CLICK]: $data")

                InsiderCallbackType.TEMP_STORE_CUSTOM_ACTION ->
                    Timber.tag("[INSIDER]").d("[TEMP_STORE_CUSTOM_ACTION]: $data")

                InsiderCallbackType.INAPP_BUTTON_CLICK -> Timber.tag("[INSIDER]")
                    .d("[INAPP_BUTTON_CLICK]: $data")

                InsiderCallbackType.TEMP_STORE_PURCHASE -> Timber.tag("[INSIDER]")
                    .d("[TEMP_STORE_PURCHASE]: $data")

                InsiderCallbackType.TEMP_STORE_ADDED_TO_CART -> Timber.tag("[INSIDER]")
                    .d("[TEMP_STORE_ADDED_TO_CART]: $data")

                InsiderCallbackType.SESSION_STARTED -> Timber.tag("[INSIDER]")
                    .d("[SESSION_STARTED]: $data")
            }
        }

        // TODO: Add your splash activity.
        //Insider.Instance.setSplashActivity(Splash.activity);

        Insider.Instance.currentUser.apply {
            setLocationOptin(true)
            setLocale("tr_TR")
        }
        Insider.Instance.startTrackingGeofence()
    }
}
