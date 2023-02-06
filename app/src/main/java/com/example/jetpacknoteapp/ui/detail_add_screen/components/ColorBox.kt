package com.example.jetpacknoteapp.ui.detail_add_screen.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpacknoteapp.ui.theme.SearchGray

@Composable
fun ColorBox(
    color: Color=SearchGray,
    isCheckIconVisible: Boolean = false,
    onClick:() -> Unit,
) {
    Box(
        contentAlignment= Alignment.Center,
        modifier = Modifier
            .size(35.dp)
            .border(
                width = 2.dp,
                color = color,
                shape = CircleShape
            ).clickable {
                onClick()
            },
    ){
        if(isCheckIconVisible){

            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "contentDescription",
                modifier = Modifier
                    .size(23.dp)
                    .background(color, CircleShape)
                    .padding(4.dp),
                tint = Color.White
            )
        }else{
            Box(
                modifier = Modifier
                    .size(23.dp)
                    .background(color, CircleShape)
                    .padding(4.dp)
            )
        }
    }
}