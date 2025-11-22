package com.app.payflixo.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.R
import com.app.payflixo.presentation.home.model.ServiceModel

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