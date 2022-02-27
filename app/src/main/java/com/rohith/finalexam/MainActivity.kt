package com.rohith.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.rohith.finalexam.db.ProductsTable
import com.rohith.finalexam.entity.Product
import com.rohith.finalexam.entity.Products

private var products : Products = Products(listOf(
    Product (1, "Kunsei Sake", "Smoked Salmon, rice", 3.95, R.drawable.i01),
    Product (2, "Sake", "Fresh Salmon, rice", 2.95, R.drawable.i02),
    Product (3, "Tai", "Tilapia, rice", 2.95, R.drawable.i03),
    Product (4, "Elbi", "Shrimp, rice", 3.95, R.drawable.i04),
    Product (5, "Maguro", "Tuna, rice", 4.5, R.drawable.i05),
    Product (6, "Unagi", "Grilled eel, rice, sesame", 4.5, R.drawable.i06),
    Product (7, "Kani Kama", "Pollock, rice", 3.95, R.drawable.i07),
    Product (8, "Tamago", "Omelet, rice", 2.95, R.drawable.i08),
    Product (9, "Kunsei Sale Tempura", "Smoked salmon, tempura, naniyori sauce", 4.25, R.drawable.i09),
    Product (10, "Hotategai", "Scallops, tempura, naniyori sauce", 4.25, R.drawable.i10),
    Product (11, "Tobiko", "Flying fish caviar", 4.25, R.drawable.i11),
    Product (12, "Masago", "Caplin fish caviar", 2.95, R.drawable.i12),
    Product (13, "Sake Tempura", "Fresh salmon, tempura, caviar, naniyori sauce", 4.5, R.drawable.i13),
    Product (14, "Maguro Tempura", "Tuna, tempura, caviar, naniyori sauce", 4.55, R.drawable.i14),
    Product (15, "Crab Tempura", "Crab, tempura, caviar, naniyori sauce", 4.55, R.drawable.i15),
    Product (16, "Shrimp Tempura", "Shrimp, tempura, caviar, naniyori sauce", 4.50, R.drawable.i16),
    Product (17, "Sakura", "Scallop, cucumber, caviar, naniyori sauce", 4.25, R.drawable.i17),
    Product (18, "Furai", "Fried shrimp", 4.25, R.drawable.i18),
    Product (19, "Kappa Makis", "Cucumber, sesame", 2.10, R.drawable.i19),
    Product (20, "Avocado Caviar", "Avocado, sesame, red caviar", 2.35, R.drawable.i20),
    Product (21, "Oshinko", "Pickled radish, sesame, sauce", 2.25, R.drawable.i21),
    Product (22, "Sake Makis", "Fresh salmon, shallots, sesame", 4.05, R.drawable.i22),
    Product (23, "Sake Makis spice", "Fresh salmon, shallots, sesame, naniyori sauce", 4.05, R.drawable.i23),
    Product (24, "Tekka Makis", "Tuna, shallots, sesame", 4.65, R.drawable.i24),
    //Product (25, "Tekka Makis spice", "Tuna, shallots, sesame, naniyori sauce", 4.60, R.drawable.i25),
    Product (26, "Una Kuy", "Eel, cucumber, sesame", 4.35, R.drawable.i26),
    Product (27, "Tai Makis", "Tilapia, shallots, sesame", 4.05, R.drawable.i27),
    Product (28, "Smoked salmon", "Smoked salmon, avocado, cream cheese, sesame", 6.05, R.drawable.i28),
    Product (29, "Shusi Naniori", "Tuna, avocado, masago, shallots, tempura, naniyori sauce", 6.35, R.drawable.i29),
    Product (30, "Shushi Shrimp", "Shrimp, avocado, tempura, cucumber, caviar, sesame, naniyori sauce", 6.05, R.drawable.i30)
))

class MainActivity : AppCompatActivity() {
    lateinit var productsTable : ProductsTable
    override fun onCreate(savedInstanceState: Bundle?) {
        productsTable = ProductsTable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val logoImage:ImageView = findViewById(R.id.main_logo)
        logoImage.setOnClickListener{
            for(product in products.products){
                if(!productsTable.productExistsInDB(product)){
                    productsTable.insertData(product)
                }
            }
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}