package com.mitraandsons.naturalwellness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitraandsons.naturalwellness.databinding.ItemCartBinding

class CartAdapter(
    private var items: List<CartManager.CartItem>,
    private val onQuantityChanged: (Int, Int) -> Unit,
    private val onRemoveItem: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: CartManager.CartItem) {
            binding.productName.text = item.product.name
            binding.productPrice.text = "$${item.product.price}"
            binding.quantity.text = item.quantity.toString()
            binding.itemTotal.text = "$${String.format("%.2f", item.product.price * item.quantity)}"

            Glide.with(binding.root)
                .load(item.product.imageUrl)
                .centerCrop()
                .into(binding.productImage)

            binding.increaseButton.setOnClickListener {
                val newQuantity = item.quantity + 1
                onQuantityChanged(item.product.id, newQuantity)
                binding.quantity.text = newQuantity.toString()
                binding.itemTotal.text = "$${String.format("%.2f", item.product.price * newQuantity)}"
            }

            binding.decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    val newQuantity = item.quantity - 1
                    onQuantityChanged(item.product.id, newQuantity)
                    binding.quantity.text = newQuantity.toString()
                    binding.itemTotal.text = "$${String.format("%.2f", item.product.price * newQuantity)}"
                }
            }

            binding.removeButton.setOnClickListener {
                onRemoveItem(item.product.id)
            }
        }
    }

    fun updateItems(newItems: List<CartManager.CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}