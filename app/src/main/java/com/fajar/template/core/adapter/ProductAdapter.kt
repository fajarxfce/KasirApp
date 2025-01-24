package com.fajar.template.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.ProductItemBinding

class ProductAdapter (private var items : List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ProductViewHolder(var binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            with(product){
                 binding.productName.text = name
                 binding.productPrice.text = price.toString()
                 binding.productStock.text = stock.toString()
            }
        }
    }

}