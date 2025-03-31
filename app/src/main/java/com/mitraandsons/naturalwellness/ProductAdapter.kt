package com.mitraandsons.naturalwellness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitraandsons.naturalwellness.databinding.ItemProductBinding

class ProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = "$${product.price}"
            binding.productDescription.text = product.description
            
            Glide.with(binding.root)
                .load(product.imageUrl)
                .centerCrop()
                .into(binding.productImage)

            binding.root.setOnClickListener { onItemClick(product) }
            binding.addToCartButton.setOnClickListener {
                CartManager.addProduct(product)
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}