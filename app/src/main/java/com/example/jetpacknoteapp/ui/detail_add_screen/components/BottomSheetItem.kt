package com.example.jetpacknoteapp.ui.detail_add_screen.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacknoteapp.ui.theme.BottomBarIcon

@Composable
fun BottomSheetItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    color: Color = BottomBarIcon
) {
    Row(
        Modifier
            .padding(start = 10.dp)
            .fillMaxWidth()
            .height(35.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = color
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),

            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = 12.sp,
        )
    }
}