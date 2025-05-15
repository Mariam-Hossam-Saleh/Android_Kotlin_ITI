package com.example.day5_app1


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ActivityA : AppCompatActivity(){
    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        isLandscape = (findViewById<View>(R.id.fragment_b) != null)
        val fragment =  ProductFragment()
        val bundle = Bundle()
        bundle.putBoolean("isLandscape",isLandscape)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_a, fragment)
            .commit()
    }

    fun showProductDetails(product: Product) {
        val fragment =  ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("product",product)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_b, fragment)
            .commit()
    }

}