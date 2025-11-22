package com.app.payflixo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.R
import com.app.payflixo.presentation.home.components.AllServicesContainer
import com.app.payflixo.presentation.home.components.BannerSlider
import com.app.payflixo.presentation.home.components.CustomBottomNavBar
import com.app.payflixo.presentation.home.components.TopUserInfoSection
import com.app.payflixo.presentation.home.components.WalletActions
import com.app.payflixo.presentation.home.components.WalletTabsSection
import com.app.payflixo.presentation.home.model.ServiceItem


val bankingServices = listOf(
    ServiceItem("AEPS Aadhaar Pay", R.drawable.ic_fingerprint),
    ServiceItem("MATM", R.drawable.ic_atm),
    ServiceItem("DMT", R.drawable.ic_transfer),
    ServiceItem("Credit Card", R.drawable.ic_credit),
    ServiceItem("Account", R.drawable.ic_account),
    ServiceItem("Loan", R.drawable.ic_loan)
)

val rechargeServices = listOf(
    ServiceItem("Mobile Recharge", R.drawable.ic_fingerprint),
    ServiceItem("DTH Recharge", R.drawable.ic_fingerprint),
    ServiceItem("Electricity Bill", R.drawable.ic_fingerprint),
    ServiceItem("Water Bill", R.drawable.ic_fingerprint),
    ServiceItem("Gas Bill", R.drawable.ic_fingerprint)
)

val travelServices = listOf(
    ServiceItem("Bus Booking", R.drawable.ic_bus),
    ServiceItem("Flight Booking", R.drawable.ic_flight),
    ServiceItem("Hotel Booking", R.drawable.ic_fingerprint)
)

@Composable
fun HomeScreen(onNavigateToAeps: () -> Unit, onNavigateToDmt: () -> Unit) {
    val scrollState = rememberScrollState()
    var selectedBottom by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 30.dp)

                .background(
                    brush = Brush.verticalGradient(
                        0f to Color(0xFF32A44D),
                        1f to Color(0xFF1D542A)
                    )
                )
        )
        {

            TopUserInfoSection()
            Spacer(modifier = Modifier.height(12.dp))
            BannerSlider()
            Spacer(modifier = Modifier.height(30.dp))
            WalletTabsSection()
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Wallet And Fund Transfer",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            WalletActions()

            Spacer(Modifier.height(20.dp))

            AllServicesContainer(
                onServiceClick = { service ->
                    when (service.title) {
                        "AEPS Aadhaar Pay" -> onNavigateToAeps()
                        "DMT" -> onNavigateToDmt()
                    }
                }
            )

        }

        CustomBottomNavBar(
            selectedIndex = selectedBottom,
            onItemSelected = { selectedBottom = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}