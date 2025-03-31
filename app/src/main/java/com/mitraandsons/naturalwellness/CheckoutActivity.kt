package com.mitraandsons.naturalwellness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mitraandsons.naturalwellness.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupOrderSummary()
        setupPlaceOrderButton()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.checkout)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupOrderSummary() {
        val items = CartManager.getCartItems()
        val summary = items.joinToString("\n") { 
            "${it.quantity}x ${it.product.name} - $${it.product.price * it.quantity}"
        }
        binding.orderSummary.text = summary
        binding.totalPrice.text = "$${String.format("%.2f", CartManager.getTotalPrice())}"
    }

    private fun setupPlaceOrderButton() {
        binding.placeOrderButton.setOnClickListener {
            if (validateForm()) {
                showOrderConfirmation()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        if (binding.nameInput.editText?.text.isNullOrEmpty()) {
            binding.nameInput.error = getString(R.string.required_field)
            isValid = false
        } else {
            binding.nameInput.error = null
        }

        if (binding.emailInput.editText?.text.isNullOrEmpty()) {
            binding.emailInput.error = getString(R.string.required_field)
            isValid = false
        } else {
            binding.emailInput.error = null
        }

        if (binding.addressInput.editText?.text.isNullOrEmpty()) {
            binding.addressInput.error = getString(R.string.required_field)
            isValid = false
        } else {
            binding.addressInput.error = null
        }

        return isValid
    }

    private fun showOrderConfirmation() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.order_confirmation)
            .setMessage(getString(R.string.order_success_message))
            .setPositiveButton(R.string.ok) { _, _ ->
                CartManager.clearCart()
                finishAffinity()
            }
            .setCancelable(false)
            .show()
    }
}