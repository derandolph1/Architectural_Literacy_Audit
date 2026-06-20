package com.example.hhchores

import kotlinx.coroutines.flow.Flow

class ChoreRepositoryImpl(
    private val taskDao: TaskDao
//    private val hhChoreDatabase: HhChoreDatabase
): ChoreRepository{
    override fun loadAllTasks(): Flow<List<Task>> {
        return taskDao.loadAllTasks()
    }
    override suspend fun insertTask(task: Task) = taskDao.insertTask(task)
}