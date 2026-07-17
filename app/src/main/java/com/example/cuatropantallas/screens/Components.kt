package com.example.cuatropantallas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cuatropantallas.ui.theme.*

internal const val NOMBRE_DESARROLLADOR = "Ing. Eriol Tuñon"

@Composable
internal fun NarutoTextField() {
    OutlinedTextField(
        value         = NOMBRE_DESARROLLADOR,
        onValueChange = {},
        readOnly      = true,
        label         = { Text("Desarrollador", color = Color.Gray, fontSize = 13.sp) },
        textStyle     = TextStyle(
            color      = NarutoOrange,
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor      = NarutoOrange,
            unfocusedBorderColor    = NarutoRed,
            focusedContainerColor   = NarutoCardBg,
            unfocusedContainerColor = NarutoCardBg
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
internal fun NarutoButton(text: String, onClick: () -> Unit) {
    Button(
        onClick  = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp),
        shape  = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = NarutoRed)
    ) {
        Text(
            text          = text,
            color         = Color.White,
            fontWeight    = FontWeight.Bold,
            fontSize      = 14.sp,
            letterSpacing = 1.sp
        )
    }
}

@Composable
internal fun ToggleRow(
    label: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(NarutoCardBg)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment    = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(icon, contentDescription = null, tint = NarutoOrange, modifier = Modifier.size(20.dp))
            Text(label, color = NarutoWhite, fontSize = 15.sp)
        }
        Switch(
            checked         = checked,
            onCheckedChange = onCheckedChange,
            colors          = SwitchDefaults.colors(
                checkedThumbColor   = NarutoWhite,
                checkedTrackColor   = NarutoRed,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = NarutoGray
            )
        )
    }
}