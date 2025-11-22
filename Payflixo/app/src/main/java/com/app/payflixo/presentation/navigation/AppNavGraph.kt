package com.app.payflixo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.payflixo.presentation.aeps.AepsScreen
import com.app.payflixo.presentation.dmt.DmtScreen
import com.app.payflixo.presentation.dmt.beneficiary.BeneficiaryListScreen
import com.app.payflixo.presentation.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME,
        modifier = modifier
    ) {

        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigateToAeps = {
                    navController.navigate(AppRoutes.AEPS)
                },
                onNavigateToDmt = {
                    navController.navigate(AppRoutes.DMT)
                }
            )
        }

        composable(AppRoutes.AEPS) {
            AepsScreen(navController)
        }

        composable(AppRoutes.DMT) {
            DmtScreen(
                navController = navController,
                onNavigateToBeneficiary = {
                    navController.navigate(AppRoutes.BENEFICIARY_LIST)
                }
            )
        }

        composable(AppRoutes.BENEFICIARY_LIST) {
            BeneficiaryListScreen(navController)
        }
    }
}
