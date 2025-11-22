package com.app.payflixo.presentation.dmt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DmtInputSheet(title: String, onSearch: () -> Unit) {
    var mobileNumber by remember { mutableStateOf("") }
    var transactionType by remember { mutableStateOf("IMPS") }
    var selectedBank by remember { mutableStateOf("Airtel Bank") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Sender Mobile Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Gray,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Transaction Type", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            CheckboxWithLabel(label = "IMPS", isSelected = transactionType == "IMPS") { transactionType = "IMPS" }
            CheckboxWithLabel(label = "NEFT", isSelected = transactionType == "NEFT") { transactionType = "NEFT" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select Bank", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            CheckboxWithLabel(label = "Airtel Bank", isSelected = selectedBank == "Airtel Bank") { selectedBank = "Airtel Bank" }
            CheckboxWithLabel(label = "Fino Bank", isSelected = selectedBank == "Fino Bank") { selectedBank = "Fino Bank" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSearch,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Search", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isSelected, onCheckedChange = { onClick() })
        Text(label, fontSize = 14.sp, color = Color.Black)
    }
}
