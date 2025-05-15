package com.example.day4_app1

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "products")
data class Product(var title: String , var price: String , var description : String, var thumbnail: String) :
    Serializable

data class ProductsResponse(
    val products: List<Product>
)

object RecycleRepo {
    fun getProducts() = listOf<Product>(
        Product("Essence Mascara Lash Princess", "9.99", "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
        Product("Eyeshadow Palette with Mirror", "19.99", "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application.", "https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/thumbnail.webp"),
        Product("Powder Canister", "14.99", 	"The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.", "https://cdn.dummyjson.com/product-images/beauty/powder-canister/thumbnail.webp"),
        Product("Red Lipstick", "12.99", "The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.", "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/thumbnail.webp"),
        Product("Red Nail Polish", "8.99", 	"The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.","https://cdn.dummyjson.com/product-images/beauty/red-nail-polish/thumbnail.webp"))
}