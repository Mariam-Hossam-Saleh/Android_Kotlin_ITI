package com.example.day5_app1

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//interface ProductsService {
//    @GET("products")
//    suspend fun getAllProducts(): List<Product>
//}

interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(): Response<ProductsResponse>
}

object RetrofitHelper{
    private const val BASE_URL = "https://dummyjson.com/"
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofitInstance.create(ProductsService::class.java)
}