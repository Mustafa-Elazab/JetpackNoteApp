package com.example.jetpacknoteapp.ui.detail_add_screen.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacknoteapp.ui.theme.Orange
import com.example.jetpacknoteapp.ui.theme.SearchGray


@Composable
fun UrlDialog(
    text: String,
    error: String? = null,
    isErrorVisible: Boolean = false,
    onTextChange: (String) -> Unit,
    onClickAdd: () -> Unit,
    onClickDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onClickDismiss() }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = SearchGray
        ) {
            Box() {
                Column(Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Language,
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Add URL",
                            modifier = Modifier.padding(start = 8.dp),
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                    NoteInputField(
                        text = text,
                        hint = "Enter Url",
                        error = error,
                        isErrorVisible = isErrorVisible,
                        onValueChange = onTextChange,
                        textStyle = TextStyle(fontSize = 13.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onClickDismiss) {
                            Text(text = "CANCEL", color = Orange)
                        }
                        TextButton(onClick = onClickAdd) {
                            Text(text = "ADD", color = Orange)
                        }
                    }
                }
            }
        }
    }
}