package com.example.todolist.widget.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<B : ViewBinding>(protected val binding: B) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(position: Int)
}