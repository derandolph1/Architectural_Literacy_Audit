package com.example.hhchores

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//import androidx.room3.Dao
//import androidx.room3.Delete
//import androidx.room3.Insert
//import androidx.room3.OnConflictStrategy
//import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: Task)
    @Query("SELECT * FROM tasks")
    fun loadAllTasks(): Flow<List<Task>>
    @Delete
    suspend fun deleteTask(tasks: Task)
}