package com.useinsider.examplewebview

import android.app.Application
import com.useinsider.insider.Insider

public class ExampleWebViewApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Insider.Instance.init(this, BuildConfig.PARTNER_NAME)
    }
}
