package com.rohith.finalexam.entity

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: Int,
    var quantity: Int = 0
) {
}