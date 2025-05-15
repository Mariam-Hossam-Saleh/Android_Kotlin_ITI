package com.example.day6_app2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.day6_app2.databinding.ActivityABinding

class ActivityA : AppCompatActivity(){
    private var isLandscape = false
    private lateinit var binding : ActivityABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLandscape = binding.fragmentB != null
        val fragment =  ProductFragment()
        val bundle = Bundle()
        bundle.putBoolean("isLandscape",isLandscape)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentA.id, fragment)
            .commit()
    }

    fun showProductDetails(product: Product) {
        val fragment =  ProductDetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("product",product)
        fragment.arguments = bundle
        binding.fragmentB?.let {
            supportFragmentManager.beginTransaction()
                .replace(it.id, fragment)
                .commit()
        }
    }

}