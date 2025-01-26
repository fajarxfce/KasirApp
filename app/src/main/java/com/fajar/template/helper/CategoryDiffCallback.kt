package com.fajar.template.helper

import androidx.recyclerview.widget.DiffUtil
import com.fajar.template.core.domain.model.Category

class CategoryDiffCallback(private val oldCategoryList: List<Category>, private val newCategoryList: List<Category>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldCategoryList.size

    override fun getNewListSize(): Int = newCategoryList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldCategoryList[oldItemPosition].id == newCategoryList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldCategoryList[oldItemPosition]
        val newProduct = newCategoryList[newItemPosition]
        return oldProduct.id == newProduct.id
    }

}