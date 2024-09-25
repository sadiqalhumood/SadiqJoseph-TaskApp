package com.example.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task.ui.theme.TaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTheme {
                Surface(){
                    TasksApp()
                }
            }
        }
    }
}

@Composable
fun TasksApp(){
    var task by remember { mutableStateOf("") }

    data class Task(val description: String) {
        var isChecked by mutableStateOf(false)
    }
    val tasks = remember { mutableStateListOf<Task>() }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Row {
            TextField(
                value = task,
                onValueChange = { task = it },
                label = { Text("Task Description") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row {
            Button(
                onClick = {
                    if (task.isNotBlank()) {
                        tasks.add(Task(description = task))
                        task = ""
                    }
                }) {
                Text("Add Task")
            }

            Button(
                onClick = {
                    tasks.removeAll { it.isChecked }
                }
            ) {
                Text("Clear Completed")
            }
        }

        LazyColumn {
            itemsIndexed(tasks) { index, taskItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = taskItem.isChecked,
                        onCheckedChange = { checked ->
                            taskItem.isChecked = checked
                        }
                    )
                    Text(text = taskItem.description)
                }
            }

        }
    }
}





