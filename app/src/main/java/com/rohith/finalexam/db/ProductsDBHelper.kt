package com.rohith.finalexam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val SQL_CREATE_TABLE =
    "CREATE TABLE ${ProductDBContract.ProductsTable.TABLE_NAME} (" +
            "${ProductDBContract.ProductsTable.PRODUCT_ID} INTEGER PRIMARY KEY, " + //"${BaseColumns._ID}"
            "${ProductDBContract.ProductsTable.TITLE} TEXT, " +
            "${ProductDBContract.ProductsTable.DESCRIPTION} TEXT, " +
            "${ProductDBContract.ProductsTable.PRICE} REAL, " +
            "${ProductDBContract.ProductsTable.IMAGE} INTEGER, " +
            "${ProductDBContract.ProductsTable.QUANTITY} INTEGER" +
            ")"

private const val DROP_TABLE = "DROP TABLE IF EXISTS ${ProductDBContract.ProductsTable.TABLE_NAME}"

class ProductsDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "products.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}