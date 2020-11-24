package com.kamil.androidtest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kamil.androidtest.R

object ImageSetter {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
     fun loadImage(view: ImageView, image: String?){
        Glide.with(view)
            .load(image)
            .placeholder(R.drawable.default_profile)
            .apply(RequestOptions().circleCrop())
            .into(view)
    }

}