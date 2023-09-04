package com.amlavati.todo.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.todo.data.models.ToDoTask
import com.amlavati.todo.data.repository.ToDoRepository
import com.amlavati.todo.util.Constants.EMPTY_STRING
import com.amlavati.todo.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {
     val searchAppBarState = mutableStateOf(SearchAppBarState.CLOSED)
     val searchTextState = mutableStateOf(EMPTY_STRING)

    private val _allTask = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTask

    fun getAllTasks() {
        viewModelScope.launch {
            toDoRepository.getAllTask.collect {
                _allTask.value = it
            }
        }
    }

}