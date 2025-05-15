package com.example.day5_app1

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {
    private var currentOrientation = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        currentOrientation = resources.configuration.orientation
        val fragment =  ProductDetailsFragment()
        val product = intent.getSerializableExtra("product") as Product
        val bundle = Bundle()
        bundle.putSerializable("product",product)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_b, fragment)
            .commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation != currentOrientation) {
            finish()
        }
    }
}