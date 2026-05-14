# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.useinsider.insider.Insider { *; }

-keep interface com.useinsider.insider.InsiderCallback { *; }
-keep class com.useinsider.insider.InsiderUser { *; }
-keep interface com.useinsider.insider.InsiderIDListener { *; }
-keep interface com.useinsider.insider.InsiderUser$InsiderIDResult { *; }
-keep class com.useinsider.insider.InsiderProduct { *; }
-keep class com.useinsider.insider.InsiderEvent { *; }
-keep class com.useinsider.insider.InsiderCallbackType { *; }
-keep class com.useinsider.insider.InsiderGender { *; }
-keep class com.useinsider.insider.InsiderIdentifiers { *; }
-keep class com.useinsider.insider.CloseButtonPosition { *; }

-keep interface com.useinsider.insider.RecommendationEngine$SmartRecommendation { *; }
-keep interface com.useinsider.insider.MessageCenterData { *; }
-keep class com.useinsider.insider.InsiderGeofence { *; }
-keep class com.useinsider.insider.HuaweiGeofence { *; }
-keep class com.useinsider.insider.ContentOptimizerDataType { *; }

-keeppackagenames com.useinsider.insider.analytics