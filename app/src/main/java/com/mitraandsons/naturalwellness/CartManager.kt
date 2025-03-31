package com.mitraandsons.naturalwellness

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(product: Product, quantity: Int = 1) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            cartItems.add(CartItem(product, quantity))
        }
    }

    fun removeProduct(productId: Int) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: Int, newQuantity: Int) {
        cartItems.find { it.product.id == productId }?.quantity = newQuantity
    }

    fun getCartItems(): List<CartItem> = cartItems.toList()

    fun getTotalPrice(): Double = cartItems.sumOf { it.product.price * it.quantity }

    fun clearCart() {
        cartItems.clear()
    }

    fun getCartItemCount(): Int = cartItems.sumOf { it.quantity }

    data class CartItem(
        val product: Product,
        var quantity: Int
    )
}