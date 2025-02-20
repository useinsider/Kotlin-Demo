package com.useinsider.kotlindemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.useinsider.insider.ContentOptimizerDataType
import com.useinsider.insider.Insider
import com.useinsider.insider.InsiderGender
import com.useinsider.insider.InsiderIdentifiers
import com.useinsider.insider.InsiderProduct
import com.useinsider.insider.InsiderUser
import com.useinsider.insider.RecommendationEngine
import com.useinsider.kotlindemo.component.InappsComponent
import com.useinsider.kotlindemo.component.TitleWithFourButtons
import com.useinsider.kotlindemo.component.TitleWithImage
import com.useinsider.kotlindemo.component.TitleWithSingleButton
import com.useinsider.kotlindemo.component.TitleWithTwoButtons
import com.useinsider.kotlindemo.component.UserIdentifierComponent
import com.useinsider.kotlindemo.component.WishlistComponent
import com.useinsider.kotlindemo.ui.theme.KotlinDemoTheme
import timber.log.Timber
import java.util.Date

class MainActivity : ComponentActivity() {

    private lateinit var currentUser: InsiderUser
    private lateinit var identifiers: InsiderIdentifiers
    private lateinit var taxonomy: Array<String>
    private lateinit var insiderExampleProduct: InsiderProduct
    private val productIDs = arrayOf("productID1", "productID2", "productID3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        currentUser = Insider.Instance.currentUser
        identifiers = InsiderIdentifiers().apply {
            addEmail("mobile@useinsider.com")
            addPhoneNumber("+901234567")
            addUserID("CRM-ID")
        }
        taxonomy = arrayOf("taxonomy1", "taxonomy2", "taxonomy3")

        setContent {
            KotlinDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(innerPadding)
                            .verticalScroll(scrollState)
                    ) {
                        TitleWithImage(
                            imageRes = R.drawable.insider_logo,
                            title = "Insider SDK Demo(Kotlin)",
                            description = "This Demo contains simple methods that you can use" +
                                    " with the Insider SDK.",
                        )

                        TitleWithSingleButton(
                            title = "User Attributes",
                            buttonText = "Set Attributes",
                            onButtonClick = { setUserAttributes() },
                        )

                        UserIdentifierComponent(
                            title = "User Identifiers",
                            onLoginClick = { loginUser() },
                            onLogoutClick = { logoutUser() },
                            onSignupClick = { signupUser() }
                        )

                        TitleWithSingleButton(
                            title = "Reinit Partner Name",
                            buttonText = "Reinit Partner Name",
                            onButtonClick = { reinitPartnerName() },
                        )

                        TitleWithSingleButton(
                            title = "Event",
                            buttonText = "Trigger Events",
                            onButtonClick = { triggerEvent() },
                        )

                        TitleWithSingleButton(
                            title = "Product",
                            buttonText = "Create Product",
                            onButtonClick = { createProduct() },
                        )

                        InappsComponent(
                            title = "In-Apps",
                            buttonText1 = "Enable Inapp",
                            buttonText2 = "Disable Inapp",
                            onButtonClick1 = { enableInApp() },
                            onButtonClick2 = { disableInApp() }
                        )

                        TitleWithFourButtons(title = "Purchase",
                            firstButtonText = "Item Added to Cart",
                            secondButtonText = "Item Remove From Cart",
                            thirdButtonText = "Item Purchase",
                            fourthButtonText = "Cart Clear",
                            firstButtonClick = { itemAddedToCart() },
                            secondButtonClick = { itemRemovedFromCart() },
                            thirdButtonClick = { itemPurchased() },
                            fourthButtonClick = { cartCleared() }
                        )

                        WishlistComponent(title = "Wishlist",
                            itemAddClick = { addItemToWishlist() },
                            itemRemoveClick = { removeItemFromWishlist() },
                            wishlistClearClick = { clearWishlist() }
                        )

                        TitleWithSingleButton(
                            title = "Smart Recommender",
                            buttonText = "Get Smart Recommender Data",
                            onButtonClick = { handleSmartRecommendation() },
                        )

                        TitleWithSingleButton(
                            title = "Social Proof",
                            buttonText = "Trigger Social Proof",
                            onButtonClick = { handleSocialProofClick() },
                        )

                        TitleWithFourButtons(title = "Page Visit Methods",
                            firstButtonText = "Home Page",
                            secondButtonText = "Product Page",
                            thirdButtonText = "Cart Page",
                            fourthButtonText = "Category Page",
                            firstButtonClick = { handleHomePageVisit() },
                            secondButtonClick = { handleProductPageVisit() },
                            thirdButtonClick = { handleCartPageVisit() },
                            fourthButtonClick = { handleCategoryPageVisit() }
                        )

                        TitleWithTwoButtons(title = "GDPR",
                            firstButtonText = "GDPR True",
                            secondButtonText = "GDPR False",
                            firstButtonClick = { handleGDPRTrueConsent() },
                            secondButtonClick = { handleGDPRFalseConsent() }
                        )

                        TitleWithSingleButton(
                            title = "Message Center",
                            buttonText = "Get Message Center Data",
                            onButtonClick = { fetchMessageCenterData() },
                        )

                        TitleWithSingleButton(
                            title = "Content Optimizer",
                            buttonText = "Get Variable with Content Optimizer",
                            onButtonClick = { fetchContentOptimizerData() },
                        )
                    }
                }
            }
        }
    }

    private fun setUserAttributes() {
        // Setting User Attributes in chainable way.
        currentUser.setName("Insider")
            .setSurname("Demo")
            .setAge(23)
            .setGender(InsiderGender.OTHER)
            .setBirthday(Date())
            .setEmailOptin(true)
            .setSMSOptin(false)
            .setPushOptin(true)
            .setLocationOptin(true)
            .setFacebookID("Facebook-ID")
            .setTwitterID("Twitter-ID")
            .setLanguage("TR")
            .setLocale("tr_TR")
            .setWhatsappOptin(true)

        // Setting User Identifiers.
        val identifiers = InsiderIdentifiers()
        identifiers.addEmail("mobile@useinsider.com")
            .addPhoneNumber("+901234567")
            .addUserID("CRM-ID")

        // Setting custom attributes.
        currentUser.setCustomAttributeWithString("string_attribute", "This is Insider.")
        currentUser.setCustomAttributeWithInt("int_attribute", 10)
        currentUser.setCustomAttributeWithDouble("double_attribute", 20.5)
        currentUser.setCustomAttributeWithBoolean("bool_attribute", true)
        currentUser.setCustomAttributeWithDate("date_attribute", Date())

        // Custom attribute with array
        val arr = arrayOf("item1", "item2", "item3")
        Insider.Instance.currentUser.setCustomAttributeWithArray("key", arr)

        // Unsetting a custom attribute
        Insider.Instance.currentUser.unsetCustomAttribute("key")
    }

    private fun loginUser() {
        currentUser.login(identifiers)
    }

    private fun logoutUser() {
        currentUser.logout()
    }

    private fun signupUser() {
        Insider.Instance.signUpConfirmation()
    }

    private fun reinitPartnerName() {
        Insider.Instance.reinitWithPartnerName("new_partner_name")
    }

    private fun triggerEvent() {
        // First event without parameters
        Insider.Instance.tagEvent("first_event").build()

        // Second event with an integer parameter
        Insider.Instance.tagEvent("second_event").addParameterWithInt("int_parameter", 10).build()

        // Third event with multiple parameters
        val insiderExampleEvent = Insider.Instance.tagEvent("third_event")
        insiderExampleEvent.addParameterWithString("string_parameter", "This is Insider.")
            .addParameterWithInt("int_parameter", 10)
            .addParameterWithDouble("double_parameter", 10.5)
            .addParameterWithBoolean("bool_parameter", true)
            .addParameterWithDate("date_parameter", Date())

        // Adding array parameter
        val arr = arrayOf("value1", "value2", "value3")
        insiderExampleEvent.addParameterWithArray("array_parameter", arr)

        // Adding multiple parameters using Map
        val parameters = HashMap<String, Any>()
        parameters["string_param"] = "insider"
        parameters["int_param"] = 42
        parameters["double_param"] = 3.14
        parameters["bool_param"] = true
        parameters["date_param"] = Date()
        parameters["array_param"] = arr

        insiderExampleEvent.addParameters(parameters).build()

        // Don't forget to call build() once you're done with parameters
        insiderExampleEvent.build()
    }

    private fun createProduct() {
        // Product information
        val productID = "productID"
        val productName = "productName"
        val taxonomy = "taxonomy"
        val imageURL = "imageURL"
        val price = 1000.5
        val currency = "currency"

        // Creating a new Insider Product
        insiderExampleProduct = Insider.Instance.createNewProduct(
            productID, productName, arrayOf(taxonomy), imageURL, price, currency
        )

        // Setting Product Attributes in chainable way
        insiderExampleProduct.setColor("color")
            .setVoucherName("voucherName")
            .setVoucherDiscount(10.5)
            .setPromotionName("promotionName")
            .setPromotionDiscount(10.5)
            .setSize("size")
            .setSalePrice(10.5)
            .setShippingCost(10.5)
            .setQuantity(10)
            .setStock(10)
            .setInStock(true)

        // Setting custom attributes
        insiderExampleProduct.setCustomAttributeWithString("string_parameter", "This is Insider.")
            .setCustomAttributeWithInt("int_parameter", 10)
            .setCustomAttributeWithDouble("double_parameter", 10.5)
            .setCustomAttributeWithBoolean("bool_parameter", true)
            .setCustomAttributeWithDate("date_parameter", Date())

        // Adding an array parameter
        val arr = arrayOf("value1", "value2", "value3")
        insiderExampleProduct.setCustomAttributeWithArray("array_parameter", arr)

        // Setting the group code
        insiderExampleProduct.setGroupCode("XXYYZZ")
    }

    private fun enableInApp() {
        Insider.Instance.enableInAppMessages()
    }

    // Method to disable in-app messages
    private fun disableInApp() {
        Insider.Instance.disableInAppMessages()
    }

    private fun itemAddedToCart() {
        insiderExampleProduct.let {
            Insider.Instance.itemAddedToCart(it)
        }
    }

    private fun itemRemovedFromCart() {
        Insider.Instance.itemRemovedFromCart("productID")
    }

    private fun itemPurchased() {
        insiderExampleProduct.let {
            Insider.Instance.itemPurchased("uniqueSaleID", it)
        }
    }

    private fun cartCleared() {
        Insider.Instance.cartCleared()
    }

    private fun addItemToWishlist() {
        insiderExampleProduct.let {
            Insider.Instance.itemAddedToWishlist(it)
        }
    }

    private fun removeItemFromWishlist() {
        Insider.Instance.itemRemovedFromWishlist("productID")
    }

    private fun clearWishlist() {
        Insider.Instance.wishlistCleared()
    }

    private fun handleSmartRecommendation() {
        // --- RECOMMENDATION ENGINE --- //

        // ID comes from your smart recommendation campaign.
        // Please follow the language code structure. For instance tr_TR.
        Insider.Instance.getSmartRecommendation(1, "tr_TR", "TRY") { recommendation ->
            Timber.tag("INSIDER").d("getSmartRecommendation: %s", recommendation.toString())
        }

        Insider.Instance.getSmartRecommendationWithProduct(
            insiderExampleProduct,
            1,
            "tr_TR"
        ) { recommendation ->
            Timber.tag("INSIDER")
                .d("getSmartRecommendationWithProduct: %s", recommendation.toString())
        }

        Insider.Instance.getSmartRecommendationWithProductIDs(productIDs,
            3,
            "en_US",
            "USD",
            RecommendationEngine.SmartRecommendation { recommendation ->
                Timber.tag("INSIDER")
                    .d("getSmartRecommendationWithProductIDs: %s", recommendation.toString())
            })
    }

    private fun handleSocialProofClick() {
        Insider.Instance.visitProductDetailPage(insiderExampleProduct)
    }

    private fun handleHomePageVisit() {
        Insider.Instance.visitHomePage()
    }

    private fun handleProductPageVisit() {
        Insider.Instance.visitProductDetailPage(insiderExampleProduct)
    }

    private fun handleCartPageVisit() {
        val insiderExampleProducts = arrayOf(insiderExampleProduct, insiderExampleProduct)
        Insider.Instance.visitCartPage(insiderExampleProducts)
    }

    private fun handleCategoryPageVisit() {
        Insider.Instance.visitListingPage(taxonomy)
    }

    private fun handleGDPRTrueConsent() {
        // --- GDPR --- //

        // MARK: Please note that by default our SDK is collecting the data so you don't
        // have to call this function if you are not asking users consents.
        // MARK: If you set false, the user will not share any data or receive any push
        // until you set back true.
        Insider.Instance.setGDPRConsent(true)
    }


    private fun handleGDPRFalseConsent() {
        // MARK: If you set false, the user will not share any data or receive any push
        // until you set back true.
        Insider.Instance.setGDPRConsent(false)
    }

    private fun fetchMessageCenterData() {
        Insider.Instance.getMessageCenterData(
            20,
            Date(1546300800),
            Date()
        )
        { messageCenterData ->  // Handle here
            Timber.tag("INSIDER").d("getMessageCenterData: %s", messageCenterData.toString())
        }
    }

    private fun fetchContentOptimizerData() {
        // String
        val contentOptimizerString = Insider.Instance.getContentStringWithName(
            "string_variable_name", "defaultValue", ContentOptimizerDataType.ELEMENT
        )
        Timber.tag("INSIDER").d("getContentStringWithName: %s", contentOptimizerString)

        // Boolean
        val contentOptimizerBool = Insider.Instance.getContentBoolWithName(
            "bool_variable_name", true, ContentOptimizerDataType.ELEMENT
        )
        Timber.tag("INSIDER").d("getContentBoolWithName: %s", contentOptimizerBool)

        // Integer
        val contentOptimizerInt = Insider.Instance.getContentIntWithName(
            "int_variable_name", 10, ContentOptimizerDataType.ELEMENT
        )
        Timber.tag("INSIDER").d("getContentIntWithName: %s", contentOptimizerInt)
    }
}
