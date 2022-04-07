package com.mmisoft.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.mmisoft.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

//Glide image loading helper file

const val DEFAULT_FOOTBALL_TEAM_IMAGE = R.drawable.ic_launcher_foreground

    @Composable
    fun loadPicture(
        url: String,
        @DrawableRes defaultImage: Int
    ): MutableState<Bitmap?>{
        val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }

        //Load the default Image
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(defaultImage)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmapState.value = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })

        //Load the actual image after it is fetched
        Glide.with(LocalContext.current)
            .asBitmap()
            .load(url)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmapState.value = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })

        return bitmapState
    }
