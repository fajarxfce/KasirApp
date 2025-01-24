package com.fajar.template.helper

import androidx.recyclerview.widget.DiffUtil
import com.fajar.template.core.domain.model.Product

class ProductDiffCallback(private val oldProductList: List<Product>, private val newProductList: List<Product>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldProductList.size

    override fun getNewListSize(): Int = newProductList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldProductList[oldItemPosition].id == newProductList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldProductList[oldItemPosition]
        val newProduct = newProductList[newItemPosition]
        return oldProduct.id == newProduct.id
    }

}