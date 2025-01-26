package com.fajar.template.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.CategoryItemBinding
import com.fajar.template.helper.CategoryDiffCallback

class CategoryAdapter () : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemLongClickCallback: OnItemLongClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Category)
    }

    interface OnItemLongClickCallback {
        fun onItemLongClicked(data: Category)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnItemLongClickCallback(onItemLongClickCallback: OnItemLongClickCallback) {
        this.onItemLongClickCallback = onItemLongClickCallback
    }

    private val listCategory = ArrayList<Category>()

    fun setListProduct(listProduct: List<Category>) {
        val diffCallback = CategoryDiffCallback(this.listCategory, listProduct)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listCategory.clear()
        this.listCategory.addAll(listProduct)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(listCategory[position])
    }

    override fun getItemCount(): Int = listCategory.size

    inner class CategoryViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category) {
            with(binding) {
                binding.name.text = category.name

                binding.cvCategory.setOnClickListener {
                    onItemClickCallback.onItemClicked(category)
                }
                binding.cvCategory.setOnLongClickListener {
                    onItemLongClickCallback.onItemLongClicked(category)
                    true
                }
            }
        }
    }

}