package com.shaz.talbook.data.remote

import com.shaz.talbook.models.Album
import com.shaz.talbook.models.Comment
import com.shaz.talbook.models.Photo
import com.shaz.talbook.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
interface MainApiService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<List<Comment>>

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Int): Call<List<Photo>>
}