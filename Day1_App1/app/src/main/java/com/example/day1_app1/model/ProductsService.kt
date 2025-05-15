package com.example.day1_app1.model

import retrofit2.Call
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    fun getProducts(): Call<ProductsResponse>
}