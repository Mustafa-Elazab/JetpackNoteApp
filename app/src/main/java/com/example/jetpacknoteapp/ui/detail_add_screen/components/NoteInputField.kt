package com.example.jetpacknoteapp.ui.detail_add_screen.components
import android.graphics.fonts.FontFamily
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacknoteapp.ui.theme.HintGray
import com.example.jetpacknoteapp.ui.theme.Orange
import com.example.jetpacknoteapp.ui.theme.Red


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    isErrorVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = true,
    textColor: Color = Color.White
) {
    Column {

    TextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColor,
            selectionColors = TextSelectionColors(
                handleColor = Orange,
                backgroundColor = Orange.copy(alpha = 0.4f)
            ),
            containerColor = Color.Transparent,
            cursorColor = Orange,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = textStyle,
        placeholder = { Text(hint, style = textStyle, color = HintGray) },

        )
    AnimatedVisibility(isErrorVisible){
        Text(
            text = error?:"",
            modifier = Modifier
                .padding(start = 13.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = Red
        )
    }
}
//    TextField(
//        value = text,
//        onValueChange = {  },
//        singleLine = singleLine,
//        modifier = modifier,
//        fontSize = fontSize,
//        placeholder = Text(hint, color = HintGray),
//        fontFamily = font,
//    )
}
