package com.fajar.template.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.CategoryItemBinding

class CategoryAdapter (private var items : List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Category>) {
        items = newItems
        notifyDataSetChanged()
    }

    class CategoryViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Category){
            with(product){
                 binding.name.text = name
            }
        }
    }

}