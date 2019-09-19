package com.shaz.talbook.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
@Parcelize
data class Photo(
    @SerializedName("albumId")
    @Expose
    var albumId: Int? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl: String? = null
) : Parcelable