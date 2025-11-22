package com.app.payflixo.presentation.aeps.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AepsInputSheet(
    title: String
) {
    var selectedMachine by remember { mutableStateOf("Morpho") }
    var aadhaarNumber by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(
                text = "Clear All",
                fontSize = 14.sp,
                color = Color(0xFF3AB557),
                modifier = Modifier.clickable { /*TODO*/ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdowns and TextFields
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AepsDropdown(label = "Select Method")
            AepsDropdown(label = "Select Bank")
            AepsTextField(
                label = "Aadhaar Number",
                value = aadhaarNumber,
                onValueChange = { aadhaarNumber = it }
            )
            AepsTextField(
                label = "Mobile Number",
                value = mobileNumber,
                onValueChange = { mobileNumber = it }
            )
            AepsTextField(
                label = "Amount",
                value = amount,
                onValueChange = { amount = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Machine Selection
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text("Select Machine", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp),modifier = Modifier.fillMaxWidth()) {
                MachineButton(
                    text = "Morpho",
                    isSelected = selectedMachine == "Morpho",
                    onClick = { selectedMachine = "Morpho" }
                )
                MachineButton(
                    text = "Mantra",
                    isSelected = selectedMachine == "Mantra",
                    onClick = { selectedMachine = "Mantra" }
                )
            }
        }
    }
}

@Composable
fun AepsDropdown(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp)
            .clickable { /* TODO */ },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
    }
}

@Composable
fun AepsTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            androidx.compose.foundation.text.BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(text = label, color = Color.Gray, fontSize = 14.sp)
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun MachineButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF3AB557) else Color(0xFF1D542A)
    val textColor = Color.White

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(45.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = textColor, fontWeight = FontWeight.Medium)
    }
}
