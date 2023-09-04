package com.amlavati.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amlavati.todo.navigation.destination.listComposable
import com.amlavati.todo.navigation.destination.taskComposable
import com.amlavati.todo.ui.viewmodels.SharedViewModel
import com.amlavati.todo.util.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            navigateToListScreen = screen.list
        )
    }
}