package com.shaz.talbook.utils

import android.widget.ImageView
import com.bumptech.glide.RequestManager

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
fun ImageView.loadImage(requestManager: RequestManager, url: String?) = requestManager.load(url).into(this)