package com.fajar.template.core.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.ItemBottomsheetCategoryBinding

class CategoryBottomsheetAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryBottomsheetAdapter.ViewHolder>(){
    private val selectedItems = SparseBooleanArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBottomsheetAdapter.ViewHolder {
        val binding = ItemBottomsheetCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryBottomsheetAdapter.ViewHolder, position: Int) {
        holder.bind(categories[position], selectedItems.get(position))
    }

    fun getSelectedCategories(): List<Category> {
        return categories.filterIndexed { index, _ -> selectedItems[index, false] }
    }

    override fun getItemCount(): Int = categories.size
    inner class ViewHolder(var binding : ItemBottomsheetCategoryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category: Category, isSelected: Boolean) {
            with(binding.checkboxCategory) {
                text = category.name
                isChecked = isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    selectedItems[adapterPosition] = isChecked
                }
            }
        }

    }

}