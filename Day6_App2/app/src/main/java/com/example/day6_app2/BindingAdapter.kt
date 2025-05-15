package com.example.day6_app2

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("myImageURL", "myImageError")
fun loadImage(view : ImageView, url : String, imageError : Drawable){
    Glide.with(view.context)
        .load(url)
        .apply( RequestOptions()
            .error(imageError))
        .into(view)
}