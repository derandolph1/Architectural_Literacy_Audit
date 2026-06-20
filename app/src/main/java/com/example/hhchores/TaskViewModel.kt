package com.example.hhchores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TaskViewModel(
    private val choreRepository: ChoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            choreRepository.loadAllTasks().collectLatest { tasks ->
                _state.emit(UiState(tasks = tasks.map { it.task }, taskCount = tasks.size))
            }
        }
    }

    fun insertTask(task: String) {
        viewModelScope.launch {
            choreRepository.insertTask(Task(task = task))
        }
    }

    data class UiState(
        val tasks: List<String> = emptyList(),
        val taskCount: Int = 0
    )
}