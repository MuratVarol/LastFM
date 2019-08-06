package com.varol.lastfm.util.binding_adapters

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.varol.lastfm.extension.setImageByUrl

/**
 * Binding adapter for image loading from url
 * setImageByUrl method using Glide background
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.setImageByUrl(url)
}


/**
 * Binding adapter for bitmap loading
 */
@BindingAdapter("setBitmap")
fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
    bitmap?.let {
        imageView.setImageBitmap(bitmap)
    }
}
