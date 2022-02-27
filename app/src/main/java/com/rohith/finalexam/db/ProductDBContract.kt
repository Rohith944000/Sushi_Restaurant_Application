package com.rohith.finalexam.db

import android.provider.BaseColumns

object ProductDBContract {

    object ProductsTable : BaseColumns {
        const val TABLE_NAME = "products"
        const val PRODUCT_ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PRICE = "price"
        const val IMAGE = "image"
        const val QUANTITY = "quantity"
    }
}