# Insider Android SDK Example

<p align="center">
  <img src="https://github.com/user-attachments/assets/4b33cb82-5896-4b0a-b2bd-a294c421d8d8" width="400">
</p>

<p align="center">
  <a href="https://insiderone.com/">Insider</a> &bull;
  <a href="https://academy.insiderone.com/docs/android-integration">Documentation</a> &bull;
  <a href="LICENSE">MIT License</a>
</p>

This project demonstrates how to integrate the [Insider Android SDK](https://academy.insiderone.com/docs/android-integration) into a Kotlin application. It includes a fully working example with push notifications, geofence tracking, and all major SDK features.

## Requirements

| Requirement | Minimum |
|---|---|
| Android | API 24 (Android 7.0) |
| Kotlin | 2.0+ |
| Android Gradle Plugin | 8.0+ |

## Getting Started

### 1. Clone the Repository

```bash
git clone git@github.com:useinsider/KotlinDemo.git
cd KotlinDemo
```

### 2. Configure Your App

Before running, update the following values with your own:

1. **Partner Name** in the module-level `app/build.gradle.kts`:

```kotlin
val partnerName = "your_partner_name"
manifestPlaceholders["partner"] = partnerName
buildConfigField("String", "PARTNER_NAME", "\"$partnerName\"")
```

This makes the partner name available both to the SDK's manifest configuration and to your Kotlin code via `BuildConfig.PARTNER_NAME`.

3. **Application ID**: Replace the `applicationId` in `app/build.gradle.kts` with the one matching your `google-services.json` file:

```kotlin
applicationId = "com.your.package.name"
```

4. **Firebase**: Add your `google-services.json` file to the `app/` directory.

5. **Huawei** *(optional)*: If you are using Huawei Messaging Service, add your `agconnect-services.json` file to the `app/` directory.

### 3. Add the SDK Dependency

The Insider SDK is hosted on a custom Maven repository. Make sure the following repository is declared in your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://mobilesdk.useinsider.com/android") }
    }
}
```

Then add the SDK dependency in your module-level `build.gradle.kts`:

```kotlin
dependencies {
    // With Huawei support
    implementation("com.useinsider:insider:15.3.0")

    // Without Huawei support
    // implementation("com.useinsider:insider:15.3.0-nh")

    // Optional: WebView support
    // implementation("com.useinsider:insiderwebview:1.0.0")
}
```

> **Note:** Use the `-nh` (no Huawei) variant if your app does not target Huawei devices. This excludes Huawei dependencies from your build.

### InsiderWebView (Optional)

If your app uses WebView and you want Insider to track in-app browser events, add the `insiderwebview` dependency. This enables the SDK to capture user interactions within WebView content.

## SDK Initialization

The SDK should be initialized in your `Application` class:

```kotlin
import com.useinsider.insider.Insider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize with your partner name (all lowercase)
        Insider.Instance.init(this, "YOUR_PARTNER_NAME")

        // Register callback handler for SDK events
        Insider.Instance.registerInsiderCallback { data, callbackType ->
            when (callbackType) {
                InsiderCallbackType.NOTIFICATION_OPEN -> { /* Handle push notification tap */ }
                InsiderCallbackType.INAPP_SEEN -> { /* Handle in-app message impression */ }
                InsiderCallbackType.INAPP_BUTTON_CLICK -> { /* Handle in-app button interaction */ }
                InsiderCallbackType.TEMP_STORE_CUSTOM_ACTION -> { /* Handle custom action */ }
                InsiderCallbackType.TEMP_STORE_PURCHASE -> { /* Handle purchase */ }
                InsiderCallbackType.TEMP_STORE_ADDED_TO_CART -> { /* Handle add to cart */ }
                InsiderCallbackType.SESSION_STARTED -> { /* Handle session start */ }
            }
        }
    }
}
```

> **Important:** Make sure your `Application` class is declared in `AndroidManifest.xml`:
>
> ```xml
> <application
>     android:name=".MyApplication"
>     ... >
> ```

## Push Notifications

### Firebase Cloud Messaging

To handle push notifications from Insider, create a `FirebaseMessagingService` and delegate Insider messages to the SDK:

```kotlin
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.useinsider.insider.Insider

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data["source"] == "Insider") {
            Insider.Instance.handleFCMNotification(applicationContext, remoteMessage)
            return
        }
    }
}
```

Register the service in your `AndroidManifest.xml`:

```xml
<service
    android:name=".MyFirebaseMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

Don't forget to add the Firebase Messaging dependency:

```kotlin
dependencies {
    implementation("com.google.firebase:firebase-messaging:25.0.1")
}
```

### Huawei Messaging Service (Optional)

If your app targets Huawei devices, add the Huawei repository and dependencies:

```kotlin
// settings.gradle.kts
maven { url = uri("https://developer.huawei.com/repo/") }

// build.gradle.kts (module)
dependencies {
    implementation("com.huawei.hms:push:6.13.0.300")
}
```

## Geofence Tracking (Optional)

To enable geofence tracking, add the location dependency and start tracking:

```kotlin
// build.gradle.kts
dependencies {
    implementation("com.google.android.gms:play-services-location:21.3.0")
}
```

```kotlin
// In your Application class
Insider.Instance.startTrackingGeofence()
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
