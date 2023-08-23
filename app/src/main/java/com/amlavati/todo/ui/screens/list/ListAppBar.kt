package com.amlavati.todo.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.amlavati.todo.R
import com.amlavati.todo.components.PriorityItem
import com.amlavati.todo.data.models.Priority

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Task")
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit,
) {
    IconButton(
        onClick =
        {
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_button)
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_button)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                leadingIcon = { PriorityItem(priority = Priority.HIGH) }
            )
            DropdownMenuItem(
                text = { },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.MEDIUM)
                },
                leadingIcon = { PriorityItem(priority = Priority.MEDIUM) }
            )
            DropdownMenuItem(
                text = { },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
                leadingIcon = { PriorityItem(priority = Priority.LOW) }
            )
            DropdownMenuItem(
                text = { },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
                leadingIcon = { PriorityItem(priority = Priority.NONE) }
            )
        }
    }
}


@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            expanded = true
        }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_button)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.delete_all)) },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                },
            )
        }
    }

}

@Preview
@Composable
fun DefaultListAppBarPreview() {
    DefaultListAppBar({}, {}, {})
}