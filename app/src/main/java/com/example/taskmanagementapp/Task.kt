package com.example.taskmanagementapp

data class Task(val id: Int, val title: String, val content: String, val due: String, val status: String = "Incomplete")
