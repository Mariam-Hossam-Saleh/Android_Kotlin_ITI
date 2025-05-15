package com.example.day4_app1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductFragment : Fragment(), OnProductClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var isLandscape = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        isLandscape = arguments?.getBoolean("isLandscape") ?: false

        recyclerAdapter = RecyclerAdapter(RecycleRepo.getProducts(), this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onProductClick(product: Product) {
        if(isLandscape) {
            (requireActivity() as ActivityA).showProductDetails(product)
        }
        else{
            val intent = Intent(requireContext(), ActivityB::class.java)
            Log.i("TAG", "===================onProductClick: ")
            intent.putExtra("product", product)
            startActivity(intent)
        }
    }
}
