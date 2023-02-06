package com.example.jetpacknoteapp.ui.detail_add_screen.screen

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.jetpacknoteapp.ui.detail_add_screen.components.*
import com.example.jetpacknoteapp.ui.detail_add_screen.uiStates.NoteDetailsEvent
import com.example.jetpacknoteapp.ui.detail_add_screen.uiStates.UiEvent
import com.example.jetpacknoteapp.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun NoteDetailsScreen(
    navController: NavController,
    viewModel: NotesDetailViewModel = hiltViewModel()
) {
    val bottomState = rememberBottomSheetScaffoldState()
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    )

    val imagePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            if (uri != null)
                viewModel.onEvent(NoteDetailsEvent.SelectedImage(uri))
        }
    )
    val scaffoldState = rememberBottomSheetScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFLow.collectLatest {
            when (it) {
                UiEvent.DeleteNote -> navController.navigateUp()
                UiEvent.SaveNote -> navController.navigateUp()
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomState,
        sheetPeekHeight = 120.dp,

        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "=",
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontSize = 13.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    viewModel.noteDetailsUiState.noteColors.forEach {
                        val colorAsInt = it.toArgb()
                        Spacer(modifier = Modifier.size(10.dp))
                        ColorBox(
                            color = it, onClick = {
                                viewModel.onEvent(NoteDetailsEvent.ClickColor(colorAsInt))
                            },
                            isCheckIconVisible = viewModel.noteDetailsUiState.noteColor == colorAsInt

                        )

                    }

                }
                Spacer(modifier = Modifier.size(20.dp))
                BottomSheetItem(icon = Icons.Filled.Image, text = "Add Image", onClick = {
                    val permissionState = permissionsState.permissions.get(0)
                    when {
                        permissionState.hasPermission -> {
                            Log.d("aaaaaaaaaaaaaaaa", "kkjjkjjkj1")
                            imagePermissionLauncher.launch(
                                arrayOf(
                                    "image/png",
                                    "image/jpg",
                                    "image/jpeg"
                                )
                            )
                        }

                        permissionState.shouldShowRationale -> {
                            permissionsState.launchMultiplePermissionRequest()
                        }

                        !permissionState.shouldShowRationale && !permissionState.hasPermission -> {
                            scaffoldState.snackbarHostState
                        }
                    }
                }


                )
                Spacer(modifier = Modifier.size(8.dp))
                BottomSheetItem(icon = Icons.Filled.Web, text = "Add URL", onClick = {

                })
                Spacer(modifier = Modifier.size(8.dp))
                if (viewModel.noteDetailsUiState.id != -1) {
                    BottomSheetItem(
                        icon = Icons.Filled.Delete,
                        text = "Delete Note",
                        color = Color.Red,
                        onClick = {
                            viewModel.onEvent(NoteDetailsEvent.DeleteNote)
                        })
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        },
        modifier = Modifier.background(LightGray),
        backgroundColor = LightGray,
        sheetBackgroundColor = BottomSheet
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(LightGray)
                .padding(vertical = 11.dp, horizontal = 0.dp),
        ) {
            if (viewModel.noteDetailsUiState.linkUiState.isLinkDialogVisible) {
                UrlDialog(
                    text = viewModel.noteDetailsUiState.linkUiState.typedLink ?: "",
                    onTextChange = { viewModel.onEvent(NoteDetailsEvent.UrlTextChanged(it)) },
                    onClickAdd = { viewModel.onEvent(NoteDetailsEvent.AddUrlDialog) },
                    onClickDismiss = { viewModel.onEvent(NoteDetailsEvent.DismissUrlDialog) },
                    error = viewModel.noteDetailsUiState.linkUiState.linkError,
                    isErrorVisible = viewModel.noteDetailsUiState.linkUiState.linkError != null
                )
            }
            Row(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                ToolBarIconButton(
                    icon = Icons.Default.ArrowBackIos,
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ToolBarIconButton(
                    icon = Icons.Default.Done,
                    onClick = { viewModel.onEvent(NoteDetailsEvent.SaveNote) },
                    modifier = Modifier.size(20.dp)
                )
            }
            NoteInputField(
                text = viewModel.noteDetailsUiState.titleInputFieldUiState.text,
                hint = "Note title",
                error = viewModel.noteDetailsUiState.titleInputFieldUiState.errorMessage,
                isErrorVisible = viewModel.noteDetailsUiState.titleInputFieldUiState.errorMessage != null,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.TitleChanged(it))
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
            Text(
                text = viewModel.noteDetailsUiState.dateTime,
                modifier = Modifier
                    .padding(start = 13.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = BottomBarIcon
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .width(width = 5.dp)
                        .height(35.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(viewModel.noteDetailsUiState.noteColor))
                )
                NoteInputField(
                    text = viewModel.noteDetailsUiState.subtitleInputFieldUiState.text,
                    hint = "Note subtitle",
                    modifier = Modifier.fillMaxWidth(),
                    error = viewModel.noteDetailsUiState.subtitleInputFieldUiState.errorMessage,
                    isErrorVisible = viewModel.noteDetailsUiState.subtitleInputFieldUiState.errorMessage != null,
                    onValueChange = {
                        viewModel.onEvent(NoteDetailsEvent.SubtitleChanged(it))
                    },
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                    ),
                    textColor = SubtitleGray
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            AnimatedVisibility(visible = viewModel.noteDetailsUiState.linkUiState.isLinkVisible) {
                Row(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.material3.Text(
                        text = viewModel.noteDetailsUiState.linkUiState.finalLink ?: "",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Orange,
                        textDecoration = TextDecoration.Underline
                    )
                    IconButton(onClick = { viewModel.onEvent(NoteDetailsEvent.DeleteUrl) }) {
                        androidx.compose.material3.Icon(
                            Icons.Default.Delete,
                            contentDescription = "delete url",
                            modifier = Modifier.size(20.dp),
                            tint = Red
                        )
                    }
                }
            }
            AnimatedVisibility(
                viewModel.noteDetailsUiState.isImageVisible,
                enter = slideInHorizontally()
            ) {
                Column {
                    Spacer(modifier = Modifier.size(5.dp))
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = Uri.parse(viewModel.noteDetailsUiState.imagePath ?: "") ?: "",
                            loading = {
                                androidx.compose.material3.CircularProgressIndicator()
                            },
                            contentDescription = null
                        )
                        IconButton(
                            onClick = { viewModel.onEvent(NoteDetailsEvent.DeleteImage) },
                            Modifier
                                .padding(top = 10.dp, end = 10.dp)
                                .size(25.dp)
                                .clip(CircleShape)
                                .background(Red)
                                .padding(4.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White,
                            )
                        }

                    }
                }

            }
            NoteInputField(
                text = viewModel.noteDetailsUiState.descriptionInputFieldUiState.text,
                hint = "Type note here",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                error = viewModel.noteDetailsUiState.descriptionInputFieldUiState.errorMessage,
                isErrorVisible = viewModel.noteDetailsUiState.descriptionInputFieldUiState.errorMessage != null,
                onValueChange = {
                    viewModel.onEvent(NoteDetailsEvent.NoteTextChanged(it))
                },
                singleLine = false,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                )
            )
        }
    }
}