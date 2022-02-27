package com.rohith.finalexam.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.rohith.finalexam.entity.Product

class ProductsTable(context: Context) {
    private val dbHelper = ProductsDBHelper(context)
    fun insertData(product: Product) {
        //Map of column name + row value
        val values = ContentValues().apply {
            put(ProductDBContract.ProductsTable.PRODUCT_ID, product.id)
            put(ProductDBContract.ProductsTable.TITLE, product.title)
            put(ProductDBContract.ProductsTable.DESCRIPTION, product.description)
            put(ProductDBContract.ProductsTable.PRICE, product.price)
            put(ProductDBContract.ProductsTable.IMAGE, product.image)
            put(ProductDBContract.ProductsTable.QUANTITY, product.quantity)
        }

        val writeToDb = dbHelper.writableDatabase //EXPENSIVE if DB is closed
        val newRowId = writeToDb.insert(ProductDBContract.ProductsTable.TABLE_NAME, null, values)
    }

    fun productExistsInDB(product: Product): Boolean {

        for (productInDB in getAll()) {
            if (product.id == productInDB.id) {
                return true
            }
        }
        return false
    }

    fun getAll(): List<Product> {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            ProductDBContract.ProductsTable.PRODUCT_ID,
            ProductDBContract.ProductsTable.TITLE,
            ProductDBContract.ProductsTable.DESCRIPTION,
            ProductDBContract.ProductsTable.PRICE,
            ProductDBContract.ProductsTable.IMAGE,
            ProductDBContract.ProductsTable.QUANTITY,
        )

        //WHERE PART only to avoid SQL Injection
        val selection =
            "${ProductDBContract.ProductsTable.TITLE} = ? AND ${ProductDBContract.ProductsTable.PRODUCT_ID} = ?"
        val selectionArgs = arrayOf("coldplay", 1456)

        //Sorting
        val orderBy = "${ProductDBContract.ProductsTable.TITLE} DESC"

        val cursor = readFromDb.query(
            ProductDBContract.ProductsTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )
        val productsList = mutableListOf<Product>()

        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                val product = Product(
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.PRODUCT_ID)),
                    getString(getColumnIndexOrThrow(ProductDBContract.ProductsTable.TITLE)),
                    getString(getColumnIndexOrThrow(ProductDBContract.ProductsTable.DESCRIPTION)),
                    getDouble(getColumnIndexOrThrow(ProductDBContract.ProductsTable.PRICE)),
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.IMAGE)),
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.QUANTITY))
                )
                productsList.add(product)

            }
        }

        cursor.close()
        return productsList
    }

    fun getAllWithQuantities(): List<Product> {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            ProductDBContract.ProductsTable.PRODUCT_ID,
            ProductDBContract.ProductsTable.TITLE,
            ProductDBContract.ProductsTable.DESCRIPTION,
            ProductDBContract.ProductsTable.PRICE,
            ProductDBContract.ProductsTable.IMAGE,
            ProductDBContract.ProductsTable.QUANTITY,
        )

        //WHERE PART only to avoid SQL Injection
        val selection =
            "${ProductDBContract.ProductsTable.QUANTITY} > ?"
        val selectionArgs = arrayOf("0")

        //Sorting
        val orderBy = "${ProductDBContract.ProductsTable.TITLE} DESC"

        val cursor = readFromDb.query(
            ProductDBContract.ProductsTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            orderBy
        )
        val productsList = mutableListOf<Product>()

        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                val product = Product(
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.PRODUCT_ID)),
                    getString(getColumnIndexOrThrow(ProductDBContract.ProductsTable.TITLE)),
                    getString(getColumnIndexOrThrow(ProductDBContract.ProductsTable.DESCRIPTION)),
                    getDouble(getColumnIndexOrThrow(ProductDBContract.ProductsTable.PRICE)),
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.IMAGE)),
                    getInt(getColumnIndexOrThrow(ProductDBContract.ProductsTable.QUANTITY))
                )
                productsList.add(product)

            }
        }

        cursor.close()
        return productsList
    }

    fun update(product: Product): Boolean {
        val dbWrite = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ProductDBContract.ProductsTable.PRODUCT_ID, product.id)
            put(ProductDBContract.ProductsTable.TITLE, product.title)
            put(ProductDBContract.ProductsTable.DESCRIPTION, product.description)
            put(ProductDBContract.ProductsTable.PRICE, product.price)
            put(ProductDBContract.ProductsTable.IMAGE, product.image)
            put(ProductDBContract.ProductsTable.QUANTITY, product.quantity)
        }
        val selection = "${ProductDBContract.ProductsTable.PRODUCT_ID}=? "
        val selectionArgs = arrayOf(product.id.toString())
        val rowsUpdated = dbWrite.update(
            ProductDBContract.ProductsTable.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )

        if (rowsUpdated >= 1) {
            return true
        }
        return false
    }

}