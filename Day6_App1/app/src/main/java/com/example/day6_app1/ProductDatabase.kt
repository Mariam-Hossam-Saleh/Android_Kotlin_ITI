package com.example.day6_app1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Product::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductDAO
    companion object {
        @Volatile
        private var INSTANCE : ProductDatabase? = null
        fun getInstance(context : Context) : ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ProductDatabase::class.java, "products_database")
                    .build()
                INSTANCE = instance
                instance}
        }
    }
}