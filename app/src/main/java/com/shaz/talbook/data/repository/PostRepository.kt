package com.shaz.talbook.data.repository

import com.shaz.talbook.data.remote.PostApiService
import com.shaz.talbook.models.Post
import com.shaz.talbook.utils.NetworkCallback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
class PostRepository @Inject constructor(val postApiService: PostApiService) {

    fun getPosts(success: (List<Post>) -> Unit, failure: (String) -> Unit) {
        postApiService.getPosts().enqueue(object : NetworkCallback<List<Post>>() {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }
}