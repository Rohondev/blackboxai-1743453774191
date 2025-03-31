package com.mitraandsons.naturalwellness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.mitraandsons.naturalwellness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupProductGrid()
        setupCartButton()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setupProductGrid() {
        productAdapter = ProductAdapter(Product.getSampleProducts()) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java).apply {
                putExtra("product", product)
            }
            startActivity(intent)
        }

        binding.productGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
        }
    }

    private fun setupCartButton() {
        binding.cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        updateCartBadge()
    }

    private fun updateCartBadge() {
        val count = CartManager.getCartItemCount()
        binding.cartBadge.text = if (count > 0) count.toString() else ""
    }

    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }
}