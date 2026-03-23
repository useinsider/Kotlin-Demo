package com.useinsider.kotlindemo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.useinsider.kotlindemo.screen.CustomEventScreen
import com.useinsider.kotlindemo.screen.CustomUserAttributesScreen
import com.useinsider.kotlindemo.screen.MainScreen
import com.useinsider.kotlindemo.viewmodel.CustomAttributesViewModel
import com.useinsider.kotlindemo.viewmodel.CustomEventViewModel
import com.useinsider.kotlindemo.viewmodel.MainViewModel

object Routes {
    const val MAIN = "main"
    const val CUSTOM_EVENT = "custom_event"
    const val CUSTOM_USER_ATTRIBUTES = "custom_user_attributes"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    val mainViewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.MAIN) {
        composable(Routes.MAIN) {
            MainScreen(
                viewModel = mainViewModel,
                onNavigateToCustomEvent = { navController.navigate(Routes.CUSTOM_EVENT) },
                onNavigateToCustomAttributes = { navController.navigate(Routes.CUSTOM_USER_ATTRIBUTES) }
            )
        }
        composable(Routes.CUSTOM_EVENT) {
            val eventViewModel: CustomEventViewModel = viewModel()
            CustomEventScreen(
                viewModel = eventViewModel,
                onBack = { navController.popBackStack() },
                onEventSent = { result ->
                    mainViewModel.updatePrintLabel(result)
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.CUSTOM_USER_ATTRIBUTES) {
            val attrsViewModel: CustomAttributesViewModel = viewModel()
            CustomUserAttributesScreen(
                viewModel = attrsViewModel,
                onBack = { navController.popBackStack() },
                onAttributesSet = { result ->
                    mainViewModel.updatePrintLabel(result)
                    navController.popBackStack()
                }
            )
        }
    }
}
