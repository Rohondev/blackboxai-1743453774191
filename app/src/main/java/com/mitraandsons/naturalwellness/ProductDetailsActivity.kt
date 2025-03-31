package com.mitraandsons.naturalwellness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mitraandsons.naturalwellness.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = intent.getParcelableExtra("product")!!
        setupViews()
        setupToolbar()
    }

    private fun setupViews() {
        Glide.with(this)
            .load(product.imageUrl)
            .into(binding.productImage)

        binding.productName.text = product.name
        binding.productPrice.text = "$${product.price}"
        binding.productDescription.text = product.detailedDescription
        binding.ingredients.text = product.ingredients

        binding.addToCartButton.setOnClickListener {
            val quantity = binding.quantityPicker.value.toInt()
            CartManager.addProduct(product, quantity)
            finish()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = product.name
        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}