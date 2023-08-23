package com.amlavati.todo.navigation

import androidx.navigation.NavHostController
import com.amlavati.todo.util.Action
import com.amlavati.todo.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/{${action.name}}") {
            popUpTo(LIST_SCREEN) {
                inclusive = true
            }
        }
    }


    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}