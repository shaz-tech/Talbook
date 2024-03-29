package com.shaz.talbook.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
@Parcelize
data class Post(
    @SerializedName("userId")
    @Expose
    var userId: Int,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("body")
    @Expose
    var body: String? = null
): Parcelable