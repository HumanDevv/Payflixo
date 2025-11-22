package com.app.payflixo.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.payflixo.R
import com.app.payflixo.presentation.home.bankingServices
import com.app.payflixo.presentation.home.model.ServiceItem
import com.app.payflixo.presentation.home.rechargeServices
import com.app.payflixo.presentation.home.travelServices
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AllServicesContainer(
    onServiceClick: (ServiceItem) -> Unit
) {

    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.bottom_bg),
                contentScale = ContentScale.Fit
            )
    ) {
        Spacer(Modifier.height(5.dp))

        // small notch line
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Spacer(
                modifier = Modifier
                    .width(25.dp)
                    .height(3.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
            )
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = "All Services",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(15.dp))

        // 🔥 Animated Tab Row
        ServicesTabRow(
            selected = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        Spacer(Modifier.height(20.dp))

        // 🔥 Animated Grid Switching (slide + fade)
        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                (slideInHorizontally(animationSpec = tween(350)) { it } + fadeIn(tween(250)))
                    .with(
                        slideOutHorizontally(animationSpec = tween(350)) { -it } +
                                fadeOut(tween(250))
                    )
            }
        ) { tab ->

            val servicesList = when (tab) {
                0 -> bankingServices
                1 -> rechargeServices
                else -> travelServices
            }

            ServicesGrid(
                list = servicesList,
                onServiceClick = onServiceClick
            )
        }

        Spacer(Modifier.height(20.dp))
        MoneyServiceSlider()
        Spacer(Modifier.height(12.dp))
    }
}
