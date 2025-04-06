package com.example.KTGK_PhungDucTai_problem_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var todoList: MutableList<Todo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = todoList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val completeTextView: TextView = itemView.findViewById(R.id.completeTextView)
        fun bind(todo: Todo){
            titleTextView.text = todo.title
            completeTextView.text = if (todo.completed) "Completed" else "Not Completed"
        }
    }

    fun submitList(todos: List<Todo>) {
        this.todoList = todos.toMutableList()
        notifyDataSetChanged()
    }

}