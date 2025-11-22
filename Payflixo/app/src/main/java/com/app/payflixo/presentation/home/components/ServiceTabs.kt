package com.app.payflixo.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.R

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

            // 🔥 Animated values
            val bgColor by animateColorAsState(
                targetValue = if (isSelected) Color.Black else Color.White,
                animationSpec = tween(250)
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else colorResource(id = R.color.light_grey),
                animationSpec = tween(250)
            )

            val borderColor by animateColorAsState(
                targetValue = if (isSelected) Color.Transparent else Color.LightGray,
                animationSpec = tween(250)
            )

            // Scale animation on press
            var pressed by remember { mutableStateOf(false) }

            val scale by animateFloatAsState(
                targetValue = if (pressed) 0.92f else 1f,
                animationSpec = tween(120)
            )

            Box(
                modifier = Modifier
                    .scale(scale)
                    .clip(RoundedCornerShape(20))
                    .background(bgColor)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(20)
                    )
                    .clickable(
                        onClick = {
                            pressed = true
                            onTabSelected(index)
                        },
                        onClickLabel = null
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            // return scale to normal after click
                            awaitPointerEvent()
                            pressed = false
                        }
                    }
            ) {
                Text(
                    text = item,
                    fontSize = 13.sp,
                    color = textColor
                )
            }
        }
    }
}
