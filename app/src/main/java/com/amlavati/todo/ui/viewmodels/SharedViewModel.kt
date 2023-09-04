package com.amlavati.todo.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.todo.data.models.ToDoTask
import com.amlavati.todo.data.repository.ToDoRepository
import com.amlavati.todo.util.Constants.EMPTY_STRING
import com.amlavati.todo.util.RequestState
import com.amlavati.todo.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    private val _allTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTask

    fun getAllTasks() {
        _allTask.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                toDoRepository.getAllTask.collect {
                    _allTask.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTask.value = RequestState.Error(e)
        }
    }

}