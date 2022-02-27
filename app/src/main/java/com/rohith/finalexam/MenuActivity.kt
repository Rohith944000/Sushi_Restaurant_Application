package com.rohith.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.rohith.finalexam.db.ProductsTable
import com.rohith.finalexam.entity.Product
import com.rohith.finalexam.entity.Products
import com.rohith.finalexam.recyclerview.ProductAdapter

class MenuActivity : AppCompatActivity() {

    private val productAdapter = ProductAdapter()
    lateinit var productsTable: ProductsTable
    private lateinit var products: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        productsTable = ProductsTable(this)
        val checkoutButton: Button = findViewById(R.id.menu_checkout)

        val recyclerView = findViewById<RecyclerView>(R.id.main_products_view)
        recyclerView.adapter = productAdapter
        products = productsTable.getAll()
        productAdapter.changeData(products)
        recyclerView.setHasFixedSize(true)

        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}