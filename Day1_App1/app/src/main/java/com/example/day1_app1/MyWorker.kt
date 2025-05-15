package com.example.day1_app1

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.day1_app1.model.Product
import com.example.day1_app1.model.ProductsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyWorker(appContext : Context, workerParams : WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
     var apic=   Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsService::class.java)

        products = apic.getProducts().execute().body()?.products ?: emptyList()


        return Result.success()
    }

    companion object fetchedData{
        var products : List<Product> = emptyList()
    }

}