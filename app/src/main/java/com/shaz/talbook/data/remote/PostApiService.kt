package com.shaz.talbook.data.remote

import com.shaz.talbook.models.Post
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
interface PostApiService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}