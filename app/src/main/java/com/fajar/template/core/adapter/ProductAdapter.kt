package com.fajar.template.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.ProductItemBinding
import com.fajar.template.helper.ProductDiffCallback
import com.fajar.template.ui.home.product.ProductFragment
import com.google.android.material.snackbar.Snackbar

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var onItemLongClickCallback: OnItemLongClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }

    interface OnItemLongClickCallback {
        fun onItemLongClicked(data: Product)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnItemLongClickCallback(onItemLongClickCallback: OnItemLongClickCallback) {
        this.onItemLongClickCallback = onItemLongClickCallback
    }

    private val listProduct = ArrayList<Product>()
    fun setListProduct(listProduct: List<Product>) {
        val diffCallback = ProductDiffCallback(this.listProduct, listProduct)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listProduct.clear()
        this.listProduct.addAll(listProduct)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listProduct[position])

    }

    override fun getItemCount(): Int = listProduct.size


    inner class ProductViewHolder(var binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                binding.productName.text = product.name
                binding.productPrice.text = product.sellPrice.toString()
                binding.productStock.text = product.stock.toString()
                binding.cardProduct.setOnClickListener {
                    onItemClickCallback.onItemClicked(product)
                }
                binding.cardProduct.setOnLongClickListener {
                    onItemLongClickCallback.onItemLongClicked(product)
                    true
                }
            }
        }
    }

}