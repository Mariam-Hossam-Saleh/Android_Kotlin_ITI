package com.example.day6_app1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDAO {
    @Query("SELECT * FROM products")
    suspend fun getAllProductsFromDB() : List<Product>

    @Insert
    suspend fun insertProduct(product: Product) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProducts(products: List<Product>)


    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product) : Int
}