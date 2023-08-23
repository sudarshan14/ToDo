package com.amlavati.todo.data.repository

import com.amlavati.todo.data.ToDoDao
import com.amlavati.todo.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTask: Flow<List<ToDoTask>> = toDoDao.getAllTask()

    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()

    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int) = toDoDao.getSelectedTask(taskId)

    suspend fun addTask(toDoTask: ToDoTask) {
        toDoDao.addTask(toDoTask)
    }

    suspend fun updateTask(toDoTask: ToDoTask) {
        toDoDao.updateTask(toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask) {
        toDoDao.deleteTask(toDoTask)
    }

    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTask()
    }

    fun searchDatabase(searchQuery: String) = toDoDao.searchDatabase(searchQuery)

}