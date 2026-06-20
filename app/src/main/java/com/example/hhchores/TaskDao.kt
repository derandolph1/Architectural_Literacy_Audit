package com.example.hhchores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: Task)
    @Query("SELECT * FROM tasks")
    fun loadAllTasks(): Flow<List<Task>>
}