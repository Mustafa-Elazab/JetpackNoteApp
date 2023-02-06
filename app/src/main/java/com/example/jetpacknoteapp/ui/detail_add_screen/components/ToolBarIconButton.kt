package com.example.jetpacknoteapp.ui.detail_add_screen.components
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpacknoteapp.ui.theme.BottomBarIcon

@Composable
fun ToolBarIconButton(icon: ImageVector, modifier: Modifier =Modifier,onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            icon,
            contentDescription = null,
            modifier = modifier,
            tint = BottomBarIcon
        )
    }
}