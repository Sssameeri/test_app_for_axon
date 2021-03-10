package com.drewrick.testappforaxon.view.adapter.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.drewrick.testappforaxon.R
import com.drewrick.testappforaxon.utils.beautyDate

@BindingAdapter("app:setProfilePhoto")
fun setProfilePhoto(imageView: ImageView, url: String) {

    val requestOptions = RequestOptions()
        .centerCrop()
        .override(128, 128)
        .transform(CenterCrop(), RoundedCorners(1000))

    Glide
        .with(imageView)
        .asDrawable()
        .load(url)
        .apply(requestOptions)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                target?.onLoadFailed(
                    ContextCompat.getDrawable(
                        imageView.context,
                        R.drawable.ic_android_black_24dp
                    )
                )
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let {
                    target?.onResourceReady(
                        resource,
                        DrawableCrossFadeTransition(1000, isFirstResource)
                    )
                }
                return true
            }
        })
        .into(imageView)
}

@BindingAdapter("app:setDetailProfilePhoto")
fun setDetailProfilePhoto(imageView: ImageView, url: String) {

    val requestOptions = RequestOptions()
        .centerCrop()
        .override(512, 512)
        .transform(CenterCrop(), RoundedCorners(1000))

    Glide
        .with(imageView)
        .asDrawable()
        .load(url)
        .apply(requestOptions)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                target?.onLoadFailed(
                    ContextCompat.getDrawable(
                        imageView.context,
                        R.drawable.ic_android_black_24dp
                    )
                )
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let {
                    target?.onResourceReady(
                        resource,
                        DrawableCrossFadeTransition(1000, isFirstResource)
                    )
                }
                return true
            }
        })
        .into(imageView)
}

@BindingAdapter("app:setDateOfBirth")
fun setDateOfBirth(textView: TextView, date: String) {
    textView.text = date.beautyDate()
}

@BindingAdapter("app:setPersonFirstName", "app:setPersonLastName", requireAll = true)
fun setPersonName(textView: TextView, personFirstName: String, personLastName: String) {
    textView.text = String.format(
        textView.context.getString(R.string.person_first_last_name),
        personFirstName,
        personLastName
    )
}