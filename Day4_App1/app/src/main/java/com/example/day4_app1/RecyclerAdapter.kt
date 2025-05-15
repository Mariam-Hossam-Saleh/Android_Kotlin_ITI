package com.example.day4_app1

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide



class RecyclerAdapter(var products : List<Product>, var myListener : OnProductClickListener)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
        lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.title.text = currentProduct.title;
        Glide.with(context).load(currentProduct.thumbnail).into(holder.thumbnail)

        holder.thumbnail.setOnClickListener {
            myListener.onProductClick(currentProduct)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(myItem  : View) : RecyclerView.ViewHolder(myItem) {
        val title: TextView = myItem.findViewById(R.id.productTitle)
        val thumbnail: ImageView = myItem.findViewById(R.id.prodcutImage)
    }
}