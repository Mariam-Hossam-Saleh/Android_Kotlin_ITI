package com.example.day6_app1

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.day6_app1.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {
    private var currentOrientation = 0
    private lateinit var binding : ActivityBBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentOrientation = resources.configuration.orientation
        val fragment =  ProductDetailsFragment()
        val product = intent.getSerializableExtra("product") as Product
        val bundle = Bundle()
        bundle.putSerializable("product",product)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentB.id, fragment)
            .commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation != currentOrientation) {
            finish()
        }
    }
}