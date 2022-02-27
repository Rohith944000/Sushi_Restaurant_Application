package com.rohith.finalexam

import android.content.DialogInterface
import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rohith.finalexam.db.ProductsTable
import com.rohith.finalexam.entity.Product
import com.rohith.finalexam.recyclerview.CheckAdapter
import com.rohith.finalexam.recyclerview.ProductAdapter

class CheckoutActivity : AppCompatActivity() {
    var formatter: NumberFormat = NumberFormat.getCurrencyInstance()
    private val checkAdapter = CheckAdapter()
    lateinit var productsTable: ProductsTable
    private lateinit var products: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        productsTable = ProductsTable(this)
        products = productsTable.getAllWithQuantities()
        val purchaseButton: Button = findViewById(R.id.check_Purchase)

        val totalValue: TextView = findViewById(R.id.check_total)
        var totalPrice: Double = 0.0
        for (product in products) {
            totalPrice += (product.quantity * product.price)
        }
        var moneyString: String = formatter.format(totalPrice)
        totalValue.text = moneyString

        purchaseButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
                setTitle(R.string.dialogTitle)
            }
            builder.setPositiveButton(R.string.positiveButton, this::dialogClick)
            builder.setNegativeButton(R.string.negativeButton, this::dialogClick)
            builder.setNeutralButton(R.string.neutralButton, this::dialogClick)
            builder.show()
        }


        val recyclerView = findViewById<RecyclerView>(R.id.check_products_view)
        recyclerView.adapter = checkAdapter
        checkAdapter.changeData(products)
        recyclerView.setHasFixedSize(true)


    }

    private fun dialogClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                for (product in products) {
                    product.quantity = 0
                    productsTable.update(product)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }
            DialogInterface.BUTTON_NEGATIVE -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
            DialogInterface.BUTTON_NEUTRAL -> {
            }
        }

    }
}