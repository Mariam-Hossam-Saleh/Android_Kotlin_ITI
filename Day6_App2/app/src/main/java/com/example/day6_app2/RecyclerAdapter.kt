package com.example.day6_app2

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.day6_app2.databinding.ItemProductBinding


class RecyclerAdapter(var products : List<Product>, var myListener :OnProductClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    lateinit var binding : ItemProductBinding

    class ViewHolder(var binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.binding.product = currentProduct
        holder.binding.listener = myListener
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun onupdate() {
        notifyDataSetChanged()
    }
}