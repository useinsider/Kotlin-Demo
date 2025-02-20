# Insider Kotlin Demo App <img src="https://github.com/github/explore/raw/main/topics/kotlin/kotlin.png" alt="java" width="50" height="50"/>
For more information about android integration please check the [link](https://academy.useinsider.com/docs/android-integration)

Check the changelogs 👉 [here](https://academy.useinsider.com/docs/android-sdk-changelog)

## Before getting the build:

1. Change the partner name with yours in the [DemoApplication.kt](https://github.com/useinsider/Kotlin-Demo/blob/main/app/src/main/java/com/useinsider/kotlindemo/DemoApplication.kt#L16) file
2. Add your partner name to manifestPlaceholders in the module-level [build.gradle](https://github.com/useinsider/Kotlin-Demo/blob/main/app/build.gradle.kts#L21) file
3. Add your google-service.json
4. Add your agconnect-services.json (if you are using huawei messaging service)
5. Replace the [applicationId](https://github.com/useinsider/Kotlin-Demo/blob/main/app/build.gradle.kts#L13) with the one in your google-service.json file
6. Add [yourAdmobAppId](https://github.com/useinsider/Kotlin-Demo/blob/main/app/src/main/AndroidManifest.xml#L38)

 ## Preview
 ![Kotlin Demo](https://github.com/user-attachments/assets/0135388f-fb3f-4987-ac13-c76f5bfa9ad6)
