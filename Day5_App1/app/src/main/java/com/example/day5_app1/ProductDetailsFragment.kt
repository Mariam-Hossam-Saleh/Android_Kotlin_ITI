package com.example.day5_app1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductDetailsFragment : Fragment() {
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getSerializable("product") as Product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        view.findViewById<TextView>(R.id.productTitle).text = product.title
        view.findViewById<TextView>(R.id.productPrice).text = product.price
        view.findViewById<TextView>(R.id.productDescription).text = product.description
        Glide.with(requireContext()).load(product.thumbnail)
            .into(view.findViewById(R.id.prodcutImage))
        return view
    }

}
