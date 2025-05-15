package com.example.day6_app1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.day6_app1.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {
    private lateinit var product: Product
    private  lateinit var binding : FragmentProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getSerializable("product") as Product
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        binding.productTitle.text = product.title
        binding.productPrice.text = product.price
        binding.productDescription.text = product.description

        Glide.with(requireContext()).load(product.thumbnail)
            .into(binding.prodcutImage)
        return binding.root
    }

}
