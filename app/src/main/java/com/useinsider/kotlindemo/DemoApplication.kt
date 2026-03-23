package com.useinsider.kotlindemo

import android.app.Application
import com.useinsider.insider.Insider
import com.useinsider.insider.InsiderCallbackType
import com.useinsider.kotlindemo.callback.CallbackStore

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO: Please change with your partner name.
        // Make sure that all the letters are lowercase.
        Insider.Instance.init(this, "your_partner_name")

        Insider.Instance.registerInsiderCallback { data, callbackType ->
            CallbackStore.update("$data")
            val label = when (callbackType) {
                InsiderCallbackType.NOTIFICATION_OPEN -> "NOTIFICATION_OPEN"
                InsiderCallbackType.INAPP_SEEN -> "INAPP_SEEN"
                InsiderCallbackType.INAPP_BUTTON_CLICK -> "INAPP_BUTTON_CLICK"
                InsiderCallbackType.TEMP_STORE_CUSTOM_ACTION -> "TEMP_STORE_CUSTOM_ACTION"
                InsiderCallbackType.TEMP_STORE_PURCHASE -> "TEMP_STORE_PURCHASE"
                InsiderCallbackType.TEMP_STORE_ADDED_TO_CART -> "TEMP_STORE_ADDED_TO_CART"
                InsiderCallbackType.SESSION_STARTED -> "SESSION_STARTED"
            }
            println("[$label]: $data")
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
