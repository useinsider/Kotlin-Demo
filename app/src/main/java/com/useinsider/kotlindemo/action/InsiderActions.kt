package com.useinsider.kotlindemo.action

import com.useinsider.insider.ContentOptimizerDataType
import com.useinsider.insider.Insider
import com.useinsider.insider.InsiderGender
import com.useinsider.insider.InsiderIdentifiers
import com.useinsider.insider.InsiderProduct
import com.useinsider.kotlindemo.model.EventParameter
import com.useinsider.kotlindemo.model.ParameterType
import java.util.Date

object InsiderActions {

    private val currentUser get() = Insider.Instance.currentUser

    private fun createSampleProduct(): InsiderProduct {
        return Insider.Instance.createNewProduct(
            "productID", "productName", arrayOf("taxonomy"), "imageURL", 1000.5, "USD"
        )
    }

    private fun createSampleIdentifiers(): InsiderIdentifiers {
        return InsiderIdentifiers().apply {
            addEmail("mobile@useinsider.com")
            addPhoneNumber("+901234567")
            addUserID("CRM-ID")
        }
    }

    // -- Custom --

    fun runCustomAction(callback: (String) -> Unit) {
        callback("Custom action executed")
    }

    // -- Core --

    fun getInsiderID(callback: (String) -> Unit) {
        val id = Insider.Instance.getInsiderID()
        callback("Insider ID: $id")
    }

    fun startTrackingGeofence(callback: (String) -> Unit) {
        Insider.Instance.startTrackingGeofence()
        callback("Geofence tracking started")
    }

    // -- Reinit --

    fun reinitPartnerName(name: String, callback: (String) -> Unit) {
        Insider.Instance.reinitWithPartnerName(name)
        callback("Reinit with: $name")
    }

    // -- Consent --

    fun setGDPRConsent(enabled: Boolean, callback: (String) -> Unit) {
        Insider.Instance.setGDPRConsent(enabled)
        callback("GDPR Consent: $enabled")
    }

    fun setMobileAppAccess(enabled: Boolean, callback: (String) -> Unit) {
        Insider.Instance.setMobileAppAccess(enabled)
        callback("Mobile App Access: $enabled")
    }

    fun enableIpCollection(enabled: Boolean, callback: (String) -> Unit) {
        Insider.Instance.enableIpCollection(enabled)
        callback("IP Collection: $enabled")
    }

    fun enableCarrierCollection(enabled: Boolean, callback: (String) -> Unit) {
        Insider.Instance.enableCarrierCollection(enabled)
        callback("Carrier Collection: $enabled")
    }

    fun enableLocationCollection(enabled: Boolean, callback: (String) -> Unit) {
        Insider.Instance.enableLocationCollection(enabled)
        callback("Location Collection: $enabled")
    }

    fun setEmailOptin(enabled: Boolean, callback: (String) -> Unit) {
        currentUser.setEmailOptin(enabled)
        callback("Email Optin: $enabled")
    }

    fun setPushOptin(enabled: Boolean, callback: (String) -> Unit) {
        currentUser.setPushOptin(enabled)
        callback("Push Optin: $enabled")
    }

    fun setLocationOptin(enabled: Boolean, callback: (String) -> Unit) {
        currentUser.setLocationOptin(enabled)
        callback("Location Optin: $enabled")
    }

    fun setSMSOptin(enabled: Boolean, callback: (String) -> Unit) {
        currentUser.setSMSOptin(enabled)
        callback("SMS Optin: $enabled")
    }

    fun setWhatsappOptin(enabled: Boolean, callback: (String) -> Unit) {
        currentUser.setWhatsappOptin(enabled)
        callback("WhatsApp Optin: $enabled")
    }

    // -- User --

    fun login(callback: (String) -> Unit) {
        val identifiers = createSampleIdentifiers()
        currentUser.login(identifiers) { insiderId ->
            callback("Login - Insider ID: $insiderId")
        }
    }

    fun logout(callback: (String) -> Unit) {
        currentUser.logout()
        callback("User logged out")
    }

    fun logoutResettingInsiderID(callback: (String) -> Unit) {
        currentUser.logoutResettingInsiderID(null) { insiderId ->
            callback("Logout Reset ID - New ID: $insiderId")
        }
    }

    // -- User Attributes --

    fun setName(value: String, callback: (String) -> Unit) {
        currentUser.setName(value)
        callback("Name set: $value")
    }

    fun setSurname(value: String, callback: (String) -> Unit) {
        currentUser.setSurname(value)
        callback("Surname set: $value")
    }

    fun setEmail(value: String, callback: (String) -> Unit) {
        currentUser.setEmail(value)
        callback("Email set: $value")
    }

    fun setPhoneNumber(value: String, callback: (String) -> Unit) {
        currentUser.setPhoneNumber(value)
        callback("Phone set: $value")
    }

    fun setAge(value: String, callback: (String) -> Unit) {
        val age = value.toIntOrNull() ?: return callback("Invalid age")
        currentUser.setAge(age)
        callback("Age set: $age")
    }

    fun setGender(value: String, callback: (String) -> Unit) {
        val gender = when (value.lowercase()) {
            "male", "0" -> InsiderGender.MALE
            "female", "1" -> InsiderGender.FEMALE
            else -> InsiderGender.OTHER
        }
        currentUser.setGender(gender)
        callback("Gender set: $value")
    }

    fun setBirthday(value: String, callback: (String) -> Unit) {
        currentUser.setBirthday(Date())
        callback("Birthday set")
    }

    fun setLanguage(value: String, callback: (String) -> Unit) {
        currentUser.setLanguage(value)
        callback("Language set: $value")
    }

    fun setLocale(value: String, callback: (String) -> Unit) {
        currentUser.setLocale(value)
        callback("Locale set: $value")
    }

    fun setFacebookID(value: String, callback: (String) -> Unit) {
        currentUser.setFacebookID(value)
        callback("Facebook ID set: $value")
    }

    fun setTwitterID(value: String, callback: (String) -> Unit) {
        currentUser.setTwitterID(value)
        callback("Twitter ID set: $value")
    }

    // -- Inapp --

    fun enableInAppMessages(callback: (String) -> Unit) {
        Insider.Instance.enableInAppMessages()
        callback("InApp messages enabled")
    }

    fun disableInAppMessages(callback: (String) -> Unit) {
        Insider.Instance.disableInAppMessages()
        callback("InApp messages disabled")
    }

    // -- Event --

    fun visitHomePage(callback: (String) -> Unit) {
        Insider.Instance.visitHomePage()
        callback("Visit Home Page")
    }

    fun visitHomePageWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "source" to "homepage",
            "campaign_id" to "camp_123",
            "page_number" to 1
        )
        Insider.Instance.visitHomePage(params)
        callback("Visit Home Page (Custom Params)")
    }

    fun visitListingPage(callback: (String) -> Unit) {
        Insider.Instance.visitListingPage(arrayOf("Electronics", "Smartphones", "iPhone"))
        callback("Visit Listing Page")
    }

    fun visitListingPageWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "page_number" to 1,
            "sort_by" to "price",
            "filter_applied" to true
        )
        Insider.Instance.visitListingPage(
            arrayOf("Electronics", "Smartphones", "iPhone"), params
        )
        callback("Visit Listing Page (Custom Params)")
    }

    fun visitProductDetailPageWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "source" to "recommendation",
            "campaign_id" to "camp_456",
            "recommendation_id" to "rec_789"
        )
        Insider.Instance.visitProductDetailPage(product, params)
        callback("Visit Product Page (Custom Params)")
    }

    fun visitCartPageWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "cart_total" to 99.99,
            "item_count" to 3,
            "discount_applied" to false
        )
        Insider.Instance.visitCartPage(arrayOf(product), params)
        callback("Visit Cart Page (Custom Params)")
    }

    // -- Product --

    fun visitProductDetailPage(callback: (String) -> Unit) {
        val product = createSampleProduct()
        Insider.Instance.visitProductDetailPage(product)
        callback("Visit Product Detail Page")
    }

    fun itemAddedToCart(callback: (String) -> Unit) {
        val product = createSampleProduct()
        Insider.Instance.itemAddedToCart(product)
        callback("Item Added to Cart")
    }

    fun itemAddedToCartWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "quantity" to 2,
            "source" to "product_page",
            "variant_id" to "var_123"
        )
        Insider.Instance.itemAddedToCart(product, params)
        callback("Item Added to Cart (Custom Params)")
    }

    fun itemRemovedFromCart(callback: (String) -> Unit) {
        Insider.Instance.itemRemovedFromCart("productID")
        callback("Item Removed from Cart")
    }

    fun itemRemovedFromCartWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "reason" to "changed_mind",
            "cart_total_after" to 50.0
        )
        Insider.Instance.itemRemovedFromCart("productID", params)
        callback("Item Removed from Cart (Custom Params)")
    }

    fun visitCartPage(callback: (String) -> Unit) {
        val product1 = createSampleProduct()
        val product2 = createSampleProduct()
        Insider.Instance.visitCartPage(arrayOf(product1, product2))
        callback("Visit Cart Page")
    }

    fun itemPurchased(callback: (String) -> Unit) {
        val product = createSampleProduct()
        Insider.Instance.itemPurchased("SALE-12345", product)
        callback("Item Purchased")
    }

    fun itemPurchasedWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "payment_method" to "credit_card",
            "discount_applied" to true,
            "shipping_method" to "express"
        )
        Insider.Instance.itemPurchased("SALE-12345", product, params)
        callback("Item Purchased (Custom Params)")
    }

    fun cartClearedWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "items_count_before" to 5,
            "total_value_before" to 250.0,
            "reason" to "checkout_completed"
        )
        Insider.Instance.cartCleared(params)
        callback("Cart Cleared (Custom Params)")
    }

    // -- Wishlist --

    fun visitWishlist(callback: (String) -> Unit) {
        val product = createSampleProduct()
        Insider.Instance.visitWishlistPage(arrayOf(product))
        callback("Visit Wishlist")
    }

    fun visitWishlistWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "wishlist_total_items" to 3,
            "wishlist_total_value" to 150.0
        )
        Insider.Instance.visitWishlistPage(arrayOf(product), params)
        callback("Visit Wishlist (Custom Params)")
    }

    fun itemAddedToWishlist(callback: (String) -> Unit) {
        val product = createSampleProduct()
        Insider.Instance.itemAddedToWishlist(product)
        callback("Item Added to Wishlist")
    }

    fun itemAddedToWishlistWithCustomParams(callback: (String) -> Unit) {
        val product = createSampleProduct()
        val params = hashMapOf<String, Any>(
            "source" to "product_page",
            "recommendation_id" to "rec_123",
            "wishlist_size" to 5
        )
        Insider.Instance.itemAddedToWishlist(product, params)
        callback("Item Added to Wishlist (Custom Params)")
    }

    fun itemRemovedFromWishlist(callback: (String) -> Unit) {
        Insider.Instance.itemRemovedFromWishlist("productID")
        callback("Item Removed from Wishlist")
    }

    fun itemRemovedFromWishlistWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "reason" to "purchased",
            "wishlist_size_after" to 2
        )
        Insider.Instance.itemRemovedFromWishlist("productID", params)
        callback("Item Removed from Wishlist (Custom Params)")
    }

    fun wishlistCleared(callback: (String) -> Unit) {
        Insider.Instance.wishlistCleared()
        callback("Wishlist Cleared")
    }

    fun wishlistClearedWithCustomParams(callback: (String) -> Unit) {
        val params = hashMapOf<String, Any>(
            "items_count_before" to 5,
            "reason" to "cleanup"
        )
        Insider.Instance.wishlistCleared(params)
        callback("Wishlist Cleared (Custom Params)")
    }

    // -- Content Optimizer --

    fun getContentStringWithoutCache(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentStringWithoutCache(
            name, "defaultValue", ContentOptimizerDataType.ELEMENT
        )
        callback("Content String (no cache): $result")
    }

    fun getContentStringWithName(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentStringWithName(
            name, "defaultValue", ContentOptimizerDataType.ELEMENT
        )
        callback("Content String: $result")
    }

    fun getContentBoolWithoutCache(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentBoolWithoutCache(
            name, true, ContentOptimizerDataType.ELEMENT
        )
        callback("Content Bool (no cache): $result")
    }

    fun getContentBoolWithName(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentBoolWithName(
            name, true, ContentOptimizerDataType.ELEMENT
        )
        callback("Content Bool: $result")
    }

    fun getContentIntWithoutCache(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentIntWithoutCache(
            name, 42, ContentOptimizerDataType.ELEMENT
        )
        callback("Content Int (no cache): $result")
    }

    fun getContentIntWithName(name: String, callback: (String) -> Unit) {
        val result = Insider.Instance.getContentIntWithName(
            name, 42, ContentOptimizerDataType.ELEMENT
        )
        callback("Content Int: $result")
    }

    // -- Custom Event --

    fun tagCustomEvent(name: String, parameters: List<EventParameter>, callback: (String) -> Unit) {
        if (name.isBlank()) {
            callback("Event name is required")
            return
        }
        val event = Insider.Instance.tagEvent(name)
        parameters.forEach { param ->
            if (param.name.isNotBlank()) {
                when (param.type) {
                    ParameterType.STRING -> event.addParameterWithString(param.name, param.value)
                    ParameterType.INTEGER -> event.addParameterWithInt(
                        param.name, param.value.toIntOrNull() ?: 0
                    )
                    ParameterType.DOUBLE -> event.addParameterWithDouble(
                        param.name, param.value.toDoubleOrNull() ?: 0.0
                    )
                    ParameterType.BOOLEAN -> event.addParameterWithBoolean(
                        param.name, param.value.toBooleanStrictOrNull() ?: false
                    )
                }
            }
        }
        event.build()
        callback("Event sent: $name (${parameters.size} params)")
    }

    // -- Custom User Attributes --

    fun setCustomAttributes(attributes: List<EventParameter>, callback: (String) -> Unit) {
        attributes.forEach { attr ->
            if (attr.name.isNotBlank()) {
                when (attr.type) {
                    ParameterType.STRING -> currentUser.setCustomAttributeWithString(
                        attr.name, attr.value
                    )
                    ParameterType.INTEGER -> currentUser.setCustomAttributeWithInt(
                        attr.name, attr.value.toIntOrNull() ?: 0
                    )
                    ParameterType.DOUBLE -> currentUser.setCustomAttributeWithDouble(
                        attr.name, attr.value.toDoubleOrNull() ?: 0.0
                    )
                    ParameterType.BOOLEAN -> currentUser.setCustomAttributeWithBoolean(
                        attr.name, attr.value.toBooleanStrictOrNull() ?: false
                    )
                }
            }
        }
        callback("Custom attributes set (${attributes.size})")
    }
}
