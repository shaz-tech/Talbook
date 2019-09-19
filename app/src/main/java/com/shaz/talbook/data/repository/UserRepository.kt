package com.shaz.talbook.data.repository

import com.shaz.talbook.data.remote.UserApiService
import com.shaz.talbook.models.User
import com.shaz.talbook.utils.NetworkCallback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class UserRepository @Inject constructor(private val userApiService: UserApiService) {

    fun getUsers(success: (List<User>) -> Unit, failure: (String) -> Unit) {
        userApiService.getUsers().enqueue(object : NetworkCallback<List<User>>() {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }

    fun getUser(userId: Int, success: (User) -> Unit, failure: (String) -> Unit) {
        userApiService.getUser(userId).enqueue(object : NetworkCallback<User>() {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }
}