package com.app.payflixo.presentation.aeps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.payflixo.R
import com.app.payflixo.presentation.aeps.components.AepsInputSheet
import com.app.payflixo.presentation.aeps.components.BankSelectionSheet
import com.app.payflixo.presentation.home.model.ServiceItem

enum class BottomSheetType {
    NONE,
    BANK_SELECTION,
    AEPS_INPUT
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AepsScreen(navController: NavHostController) {
    var currentSheet by remember { mutableStateOf(BottomSheetType.NONE) }
    var selectedAeps by remember { mutableStateOf<ServiceItem?>(null) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AEPS", fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(38.dp)
                            .clickable { /* TODO: Handle add user */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.ic_profile_add) ,
                            contentDescription = "Add User",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            val aepsServices = listOf(
                ServiceItem("AEPS-1", R.drawable.ic_fingerprint),
                ServiceItem("AEPS-2", R.drawable.ic_fingerprint)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                aepsServices.forEach { service ->
                    AepsServiceCard(
                        service = service,
                        onClick = { 
                            selectedAeps = service
                            currentSheet = BottomSheetType.BANK_SELECTION 
                        }
                    )
                }
            }
        }
    }

    if (currentSheet != BottomSheetType.NONE) {
        ModalBottomSheet(
            onDismissRequest = { currentSheet = BottomSheetType.NONE },
            sheetState = sheetState
        ) {
            when (currentSheet) {
                BottomSheetType.BANK_SELECTION -> {
                    BankSelectionSheet(
                        banks = listOf("FINO", "NSDL", "CITY UNION"),
                        onBankClick = { /*TODO*/ },
                        onNext = {
                            currentSheet = BottomSheetType.AEPS_INPUT
                        }
                    )
                }
                BottomSheetType.AEPS_INPUT -> {
                   selectedAeps?.let { AepsInputSheet(title = it.title) }
                }
                else -> {}
            }
        }
    }
}

@Composable
fun AepsServiceCard(
    service: ServiceItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(140.dp)
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
                .size(60.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = service.icon),
                contentDescription = service.title,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = service.title,
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AepsScreenPreview() {
}
