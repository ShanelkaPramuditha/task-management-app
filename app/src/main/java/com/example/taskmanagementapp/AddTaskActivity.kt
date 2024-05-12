package com.example.taskmanagementapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagementapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDBHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.taskName.text.toString()
            val content = binding.taskDescription.text.toString()
            val task = Task(0, title, content)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show()
        }
    }
}