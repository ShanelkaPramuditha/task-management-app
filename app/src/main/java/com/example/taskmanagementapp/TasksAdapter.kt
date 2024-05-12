package com.example.taskmanagementapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private var tasks: List<Task>, context: Context) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val db: TaskDBHelper = TaskDBHelper(context)
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.taskName)
        val contentTextView: TextView = itemView.findViewById(R.id.taskDescription)
        val updateButton: ImageView = itemView.findViewById(R.id.updateTask)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content

        holder.updateButton.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Update Task")
                .setMessage("Are you sure you want to update this task?")
                .setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                        putExtra("task_id", task.id)
                    }
                    holder.itemView.context.startActivity(intent)
                }
                .setNegativeButton("No", null)
                .show()
        }

        holder.deleteButton.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes") { _, _ ->
                    db.deleteTask(task.id)
                    refreshData(db.getAllTasks())
                    Toast.makeText(holder.itemView.context, "Deleted!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}