package com.app.payflixo.presentation.dmt.beneficiary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.payflixo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeneficiaryListScreen(navController: NavHostController) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Beneficiary", "Customer Info")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Domestic Money Transfer", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
                , colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF0F0F0)
                )
            )
        },
        containerColor = Color(0xFFF0F0F0)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = tabIndex, containerColor = Color(0xFFF0F0F0)) {
                tabs.forEachIndexed { index, title ->
                    Tab(selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(title) })
                }
            }
            when (tabIndex) {
                0 -> BeneficiaryTab()
                1 -> CustomerInfoTab() // Placeholder for the second tab
            }
        }
    }
}

@Composable
fun BeneficiaryTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { /* TODO: Add beneficiary */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D542A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Add New Beneficiary Account", color = Color.White, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                BeneficiaryCard(isActive = true)
            }
            item {
                BeneficiaryCard(isActive = false)
            }
        }
    }
}

@Composable
fun BeneficiaryCard(isActive: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Name", color = Color.Gray, fontSize = 12.sp)
                IconButton(onClick = { /* TODO: Delete */ }, modifier = Modifier.height(20.dp)) {
                    Icon(painterResource(id = R.drawable.ic_profile_add), contentDescription = "Delete", tint = Color.Red)
                }
            }
            Text("Sparkup Technology PVT. LTD.", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Bank", color = Color.Gray, fontSize = 12.sp)
                    Text("Kotak Mahindra Bank", fontWeight = FontWeight.Medium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("IFSC", color = Color.Gray, fontSize = 12.sp)
                    Text("Kkbk0005900", fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Account", color = Color.Gray, fontSize = 12.sp)
                    Text("1234567890", fontWeight = FontWeight.Medium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Status", color = Color.Gray, fontSize = 12.sp)
                    Text(
                        text = if (isActive) "Active" else "Inactive",
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = if (isActive) Color(0xFF3AB557) else Color.Red,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Send money */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Send Money", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun CustomerInfoTab() {
    // Placeholder for Customer Info content
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Customer Info Tab")
    }
}
