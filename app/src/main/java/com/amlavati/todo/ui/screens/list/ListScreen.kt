package com.amlavati.todo.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.amlavati.todo.R
import com.amlavati.todo.ui.viewmodels.SharedViewModel
import com.amlavati.todo.util.LogUtil
import com.amlavati.todo.util.RequestState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true, block = {
        LogUtil.printDebugLog("called launched effect getAllTasks")
        sharedViewModel.getAllTasks()
    })

    val allTasks by sharedViewModel.allTasks.collectAsState()


    val searchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = { innerPadding ->
            when (allTasks) {
                is RequestState.Loading -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = innerPadding
                    ) {
                        items(10) {
                            ShimmerListItem(
                                isLoading = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                    //ShowLoader(innerPadding)
                }

                is RequestState.Success -> {
                    ListContent(
                        tasks = allTasks,
                        navigateToTaskScreen = navigateToTaskScreen,
                        contentPadding = innerPadding
                    )
                }

                else -> {

                }
            }


        },

        floatingActionButton = {
            ListFav(navigateToTaskScreen = navigateToTaskScreen)
        },
        floatingActionButtonPosition = FabPosition.End
    )
}


@Composable
fun ListFav(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            navigateToTaskScreen(-1)
        }) {
        Icon(
            imageVector = Icons.Filled.Add,
            tint = Color.Black,
            contentDescription = stringResource(id = R.string.add_button)
        )
    }
}
