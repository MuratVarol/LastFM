package com.varol.lastfm.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.varol.lastfm.util.GlideApp

fun ImageView.setImageByUrl(url: String?) {
    if (url?.isNotEmpty() == true) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

        GlideApp.with(this.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(this)
    }
}