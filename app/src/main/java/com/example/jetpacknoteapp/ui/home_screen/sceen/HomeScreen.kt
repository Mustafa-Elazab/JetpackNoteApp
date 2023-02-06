package com.example.jetpacknoteapp.ui.home_screen.sceen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpacknoteapp.ui.home_screen.components.NoteItem
import com.example.jetpacknoteapp.ui.home_screen.components.SearchField
import com.example.jetpacknoteapp.ui.home_screen.state.HomeUiEvent
import com.example.jetpacknoteapp.ui.theme.LightGray
import com.example.jetpacknoteapp.ui.theme.Orange
import com.example.jetpacknoteapp.utils.Screen


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Scaffold(modifier = Modifier.background(LightGray),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.NoteDetailsScreen.route + "?noteId=-1"
                    )

                },
                containerColor = Orange,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()

            ) {
                Icon(Icons.Filled.Add, "Localized description", tint = LightGray)

            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
                .padding(vertical = 10.dp, horizontal = 12.dp)
        ) {
            Text(
                text = "My Notes",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(Modifier.size(15.dp))
            SearchField(
                value = viewModel.notesUiState.searchText,
                onValueChange = { viewModel.onEvent(HomeUiEvent.SearchTextChanged(it)) },
                onSearch = { viewModel.onEvent(HomeUiEvent.Search) }
            )
            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    if (!viewModel.notesUiState.searchResult.isEmpty()) {
                        item() {
                            Text(
                                text = "Search Result",
                                modifier = Modifier.padding(top = 12.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.White,
                            )
                        }
                        items(viewModel.notesUiState.searchResult) {
                            NoteItem(
                                note = it,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(),
                                onClick = {
                                    navController.navigate(
                                        Screen.NoteDetailsScreen.route + "?noteId=${it.id}"
                                    )
                                },
                            )
                        }
                    } else {
                        item {
                            Text(
                                text = "All Notes",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                        items(viewModel.notesUiState.notes, key = { it.id }) {
                            NoteItem(
                                note = it,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(),
                                onClick = {
                                    navController.navigate(
                                        Screen.NoteDetailsScreen.route + "?noteId=${it.id}"
                                    )
                                },

                                )
                        }
                    }
                })

        }
    }

}