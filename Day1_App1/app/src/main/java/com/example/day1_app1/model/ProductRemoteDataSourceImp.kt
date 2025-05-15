//package com.example.day1_app1.model
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object ProductRemoteDataSourceImp : ProductRemoteDataSource {
//    private lateinit var productService: ProductsService
//    override fun getProducts(): List<Product> {
//        Retrofit.Builder()
//            .baseUrl("https://dummyjson.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ProductsService::class.java)
//    }
//}