package com.app.payflixo.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.R

@Composable
fun WalletTabsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
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

        Spacer(Modifier.width(12.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_wallet),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )

        Spacer(Modifier.width(5.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(Modifier.width(10.dp))
    }
}