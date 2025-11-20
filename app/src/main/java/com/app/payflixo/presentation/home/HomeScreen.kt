package com.app.payflixo.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.app.payflixo.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.app.payflixo.presentation.home.model.ServiceItem


// ---------------------- Services Data --------------------------

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
data class ServiceModel(
    val title: String,
    val subtitle: String,
    val icon: Int
)

// ------------------------- HOME SCREEN --------------------------

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    var selectedBottom by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    brush = Brush.verticalGradient(
                        0f to Color(0xFF32A44D),
                        1f to Color(0xFF1D542A)
                    )
                )
        )
        {
            Spacer(modifier = Modifier.height(40.dp))

            TopUserInfoSection()
            Spacer(modifier = Modifier.height(12.dp))
            BannerSlider()
            Spacer(modifier = Modifier.height(30.dp))
            WalletTabsSection()
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Wallet And Fund Transfer",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            WalletActions()

            Spacer(Modifier.height(20.dp))

            AllServicesContainer()

        }

        CustomBottomNavBar(
            selectedIndex = selectedBottom,
            onItemSelected = { selectedBottom = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


// ----------------------- TOP SECTION ---------------------------

@Composable
fun TopUserInfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // User Info
        Column {
            Text("Hi, Wade Warren", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Available Balance ₹2000.00", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
        }

        // Push notification icon to the right
        Spacer(modifier = Modifier.weight(1f))

        // Notification Icon
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}


// ---------------------- TOP BANNER SLIDER ----------------------

@Composable
fun BannerSlider() {

    val banners = listOf(
        R.drawable.ic_flight,
        R.drawable.ic_bus,
        R.drawable.ic_flight
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(banners) { item ->
            BannerItem(image = item)
        }
    }
}


@Composable
fun BannerItem(image: Int) {
    Box(
        modifier = Modifier
            .width(320.dp)
            .height(110.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


// ------------------------- WALLET TAB --------------------------

@Composable
fun WalletTabsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        WalletChip(title = "Prepaid Wallet")
        Image(painter = painterResource(R.drawable.ic_line), contentDescription = "Line")
        WalletChip(title = "Postpaid Wallet")
    }
}

@Composable
fun WalletChip(title: String) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(40.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_wallet),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(Modifier.width(10.dp))
    }
}


// ---------------------------- WALLET ACTIONS ---------------------------

@Composable
fun WalletActions() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionItem("Self Transfer", R.drawable.ic_self)
        ActionItem("Load Wallet", R.drawable.ic_load)
        ActionItem("Top Up", R.drawable.ic_topup)
        ActionItem("Link Payment", R.drawable.ic_link)
    }
}

@Composable
fun ActionItem(title: String, icon: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, color = Color.White, fontSize = 12.sp)
    }
}


// ---------------------------- ALL SERVICES ---------------------------

@Composable
fun AllServicesContainer() {

    var selectedTab by remember { mutableStateOf(0) }

    val services = when (selectedTab) {
        0 -> bankingServices
        1 -> rechargeServices
        else -> travelServices
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.bottom_bg),
                contentScale = ContentScale.FillBounds
            )
            .padding(vertical = 20.dp)
    ) {

        Text(
            text = "All Services",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(15.dp))

        ServicesTabRow(selected = selectedTab, onTabSelected = { selectedTab = it })

        Spacer(Modifier.height(20.dp))

        ServicesGrid(
            list = services,
            onServiceClick = {
                // HANDLE SERVICE CARD CLICK HERE
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        MoneyServiceSlider()
        Spacer(modifier = Modifier.height(12.dp))
    }
}


// ---------------------- Horizontal Tabs -------------------------

@Composable
fun ServicesTabRow(selected: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Banking Services", "Recharge And Bill Pay", "Tour & Travel")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(tabs) { index, item ->

            val isSelected = index == selected

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .background(if (isSelected) Color.Black else Color.White)
                    .border(
                        width = if (isSelected) 0.dp else 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onTabSelected(index) }
            ) {
                Text(
                    text = item,
                    fontSize = 13.sp,
                    color = if (isSelected) Color.White else colorResource(id = R.color.light_grey)
                )
            }
        }
    }
}


// ---------------------- Dynamic Grid -------------------------

@Composable
fun ServicesGrid(list: List<ServiceItem>, onServiceClick: (ServiceItem) -> Unit) {

    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        list.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                rowItems.forEach { service ->
                    ServiceCard(
                        service = service,
                        onClick = { onServiceClick(service) }
                    )
                }

                if (rowItems.size < 3) {
                    repeat(3 - rowItems.size) {
                        Spacer(modifier = Modifier.size(110.dp))
                    }
                }
            }
        }
    }
}


// ---------------------- Service Card -------------------------

@Composable
fun ServiceCard(
    service: ServiceItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(height = 120.dp, width = 110.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF4FFF7), Color(0xFFDBFDE2))
                )
            )
            .clickable { onClick() }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = service.icon),
                contentDescription = service.title,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = service.title,
            color = Color.Black,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun MoneyServiceSlider() {

    val services = listOf(
        ServiceModel(
            "Send Your Money",
            "Customer Can Transfer Amount Across India.",
            R.drawable.ic_money_hand
        ),
        ServiceModel(
            "Cash Withdrawal",
            "Withdraw cash instantly using Aadhaar.",
            R.drawable.ic_money_hand
        ),
        ServiceModel(
            "Mobile Recharge",
            "Fast prepaid recharge for all networks.",
            R.drawable.ic_money_hand
        )
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth().height(70.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(services) { item ->
            MoneyServiceCard(
                title = item.title,
                subtitle = item.subtitle,
                icon = item.icon
            )
        }
    }
}

@Composable
fun MoneyServiceCard(
    title: String,
    subtitle: String,
    icon: Int
) {
    Row(
        modifier = Modifier
            .width(330.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE6FFEF))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )


        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

// ---------------------- Bottom Navigation -------------------------

@Composable
fun CustomBottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        R.drawable.ic_home,
        R.drawable.ic_document,
        R.drawable.ic_exchange,
        R.drawable.ic_support
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(30.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEachIndexed { index, iconRes ->
                val isSelected = index == selectedIndex

                Box(
                    modifier = Modifier
                        .size(60.dp, 40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected)
                                Color(0xFF3AB557)
                            else
                                Color(0xFFF3F3F3)
                        )
                        .clickable { onItemSelected(index) },
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        colorFilter = ColorFilter.tint(
                            if (isSelected)
                                Color.White
                            else
                                Color(0xFF8D8D8D)
                        )
                    )
                }
            }
        }
    }
}
