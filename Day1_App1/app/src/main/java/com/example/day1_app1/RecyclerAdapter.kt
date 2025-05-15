package com.example.day1_app1

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.day1_app1.model.Product


class RecyclerAdapter(var products : List<Product>, var myListener :OnProductClickListener)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.title.text = currentProduct.title;
        Glide.with(holder.itemView.context).load(currentProduct.thumbnail).into(holder.thumbnail)

        holder.itemView.setOnClickListener {
            myListener.onProductClick(currentProduct)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun onupdate() {
        notifyDataSetChanged()
    }
//
//    fun setProducts(products :List<Product>){
//        this.products = products
//    }

    class ViewHolder(private val item : View) : RecyclerView.ViewHolder(item) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val thumbnail: ImageView = itemView.findViewById(R.id.prodcutImage)
    }
}