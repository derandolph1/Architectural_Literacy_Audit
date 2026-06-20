package com.example.hhchores

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_id")val id: Long = 0,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "completed", defaultValue = "0") val completed: Boolean =false
)