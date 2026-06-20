package com.example.hhchores

import kotlinx.coroutines.flow.Flow

interface ChoreRepository{
    fun loadAllTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)
}