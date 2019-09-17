package com.shaz.talbook.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
data class Post(
    @SerializedName("userId")
    @Expose
    private var userId: Int,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("body")
    @Expose
    var body: String? = null
)