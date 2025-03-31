package com.mitraandsons.naturalwellness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitraandsons.naturalwellness.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupCartList()
        setupCheckoutButton()
        updateTotal()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.your_cart)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupCartList() {
        cartAdapter = CartAdapter(
            CartManager.getCartItems(),
            onQuantityChanged = { productId, newQuantity ->
                CartManager.updateQuantity(productId, newQuantity)
                updateTotal()
            },
            onRemoveItem = { productId ->
                CartManager.removeProduct(productId)
                cartAdapter.updateItems(CartManager.getCartItems())
                updateTotal()
            }
        )

        binding.cartList.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }

    private fun setupCheckoutButton() {
        binding.checkoutButton.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    private fun updateTotal() {
        binding.totalPrice.text = "$${String.format("%.2f", CartManager.getTotalPrice())}"
        binding.checkoutButton.isEnabled = CartManager.getCartItemCount() > 0
    }

    override fun onResume() {
        super.onResume()
        cartAdapter.updateItems(CartManager.getCartItems())
        updateTotal()
    }
}