package com.example.hhchores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.Room
//import androidx.room.processor.Context
import com.example.hhchores.ui.theme.HhChoresTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val taskDatabase =
            Room.databaseBuilder(
                applicationContext,
                HhChoreDatabase::class.java,
                "HhChoresDatabase"
            )
                .build()
        val taskRepository = ChoreRepositoryImpl(taskDatabase.taskDao())
        setContent {
            HhChoresTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel =
                        viewModel<TaskViewModel>(factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return TaskViewModel(taskRepository) as T
                            }
                        })
                    Task(viewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun Task(
        viewModel: TaskViewModel,
        modifier: Modifier = Modifier
    ) {
        TaskScreen(
            uiState = viewModel.state.collectAsState().value,
            onNewTaskClicked = {
                viewModel.insertTask(it)
            }, modifier = modifier
        )
    }

    @Composable
    fun TaskScreen(
        uiState: TaskViewModel.UiState,
        onNewTaskClicked: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                var textFieldText by remember {
                    mutableStateOf("")
                }
                TextField(value = textFieldText, onValueChange = {
                    textFieldText = it
                })
                Button(onClick = {
                    onNewTaskClicked(textFieldText)
                }) {
                    Text(text = "Click Me")
                }
//                Text(text = uiState.taskCount)
            }
            items(uiState.tasks.size) {
                Text(text = uiState.tasks[it])
            }

        }
    }
}

