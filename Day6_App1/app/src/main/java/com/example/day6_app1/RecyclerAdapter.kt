package com.example.day6_app1

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.day6_app1.databinding.ItemProductBinding


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
        holder.binding.productTitle.text = currentProduct.title;
        Glide.with(holder.itemView.context).load(currentProduct.thumbnail).into(holder.binding.prodcutImage)

        holder.binding.prodcutImage.setOnClickListener {
            myListener.onProductClick(currentProduct)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun onupdate() {
        notifyDataSetChanged()
    }
}