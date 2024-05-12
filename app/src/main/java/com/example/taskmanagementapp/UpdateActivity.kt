package com.example.taskmanagementapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagementapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TaskDBHelper
    private var taskId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDBHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1) {
            finish()
            return
        }

        val task = db.getTaskById(taskId)
        binding.updateTaskName.setText(task.title)
        binding.updateTaskDescription.setText(task.content)

        binding.updateButton.setOnClickListener {
            val newTitle = binding.updateTaskName.text.toString()
            val newContent = binding.updateTaskDescription.text.toString()
            val updatedTask = Task(taskId, newTitle, newContent)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
        }
    }
}