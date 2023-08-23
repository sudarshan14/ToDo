package com.amlavati.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amlavati.todo.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTask(): Flow<List<ToDoTask>>

    @Query("SELECT * from todo_table where id =:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(todoTask: ToDoTask):Int

    @Update
    suspend fun updateTask(toDoTask: ToDoTask):Int

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask):Int

    @Query("DELETE from todo_table")
    suspend fun deleteAllTask():Int


    @Query("SELECT * FROM todo_table WHERE title like :searchQuery or description like :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>


    @Query("SELECT * from todo_table order by CASE WHEN priority LIKE 'L%' THEN  1 WHEN priority LIKE '%M' THEN 2 WHEN priority LIKE '%H' THEN 3 END")
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query("SELECT * from todo_table order by CASE WHEN priority LIKE 'H%' THEN  1 WHEN priority LIKE '%M' THEN 2 WHEN priority LIKE '%L' THEN 3 END")
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}