package com.shaz.talbook.data.repository

import com.shaz.talbook.data.remote.MainApiService
import com.shaz.talbook.models.Album
import com.shaz.talbook.models.Photo
import com.shaz.talbook.utils.NetworkCallback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class AlbumRepository @Inject constructor(private val mainApiService: MainApiService) {

    fun getAlbums(success: (List<Album>) -> Unit, failure: (String) -> Unit) {
        mainApiService.getAlbums().enqueue(object : NetworkCallback<List<Album>>() {

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }

    fun getPhotos(albumId: Int, success: (List<Photo>) -> Unit, failure: (String) -> Unit) {
        mainApiService.getPhotos(albumId).enqueue(object : NetworkCallback<List<Photo>>() {

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }
}