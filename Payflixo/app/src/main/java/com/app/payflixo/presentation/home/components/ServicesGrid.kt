package com.app.payflixo.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.presentation.home.model.ServiceItem

@Composable
fun ServicesGrid(list: List<ServiceItem>, onServiceClick: (ServiceItem) -> Unit) {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        list.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
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

@Composable
fun ServiceCard(service: ServiceItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(110.dp, 120.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF4FFF7), Color(0xFFDBFDE2))
                ),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick()}
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ICON stays perfectly centered always
        Box(
            Modifier
                .size(48.dp)
                .background(Color.White, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(service.icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        // FIX: Give text a fixed height area so icon never moves
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp), // enough for 2 lines
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = service.title,
                fontSize = 12.sp,
                textAlign = TextAlign.Center

            )
        }
    }
}
