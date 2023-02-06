package com.example.jetpacknoteapp.ui.home_screen.components

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.jetpacknoteapp.ui.home_screen.state.NoteItemUiState
import com.example.jetpacknoteapp.ui.theme.NoteSubtitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: NoteItemUiState,
             modifier: Modifier = Modifier,
             onClick: () -> Unit) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(note.noteColor)),
    ) {
        if (note.isImageVisible) {
            SubcomposeAsyncImage(
                model = Uri.parse(note.imagePath ?: "") ?: "",
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
            )
        }
        Text(
            text = note.noteTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp, end = 8.dp, start = 8.dp)
        )
        Text(
            text = note.noteSubtitle,

            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = NoteSubtitle,
            modifier = Modifier.padding(
                top = 4.dp, end = 8.dp, start = 8.dp, bottom = 4.dp
            )
        )
        Text(
            text = note.noteDate,
            fontWeight = FontWeight.Normal,
            fontSize = 7.sp,
            color = NoteSubtitle,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}