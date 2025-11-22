package com.app.payflixo.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.payflixo.R

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