package com.example.day5_app1

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products")
data class Product(@PrimaryKey var title: String , var price: String , var description : String, var thumbnail: String) :
    Serializable

data class ProductsResponse(
    val products: List<Product>
)