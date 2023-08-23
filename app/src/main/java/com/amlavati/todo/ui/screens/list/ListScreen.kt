package com.amlavati.todo.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.amlavati.todo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Scaffold(
        topBar = { ListAppBar() },
        content = {
            it.calculateBottomPadding()
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

@Composable
@Preview
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}