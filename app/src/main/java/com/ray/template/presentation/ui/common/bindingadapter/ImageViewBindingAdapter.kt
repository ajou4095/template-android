package com.ray.template.presentation.ui.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ray.template.common.util.isNotEmpty

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    if (imageUrl.isNotEmpty) {
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}