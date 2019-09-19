package com.shaz.talbook.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaz.talbook.data.repository.AlbumRepository
import com.shaz.talbook.models.Album
import com.shaz.talbook.models.ResourceStatus
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class AlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {

    private val postLiveData = MutableLiveData<ResourceStatus<List<Album>>>()

    init {
        print("AlbumViewModel initialized")
        pullAlbums()
    }

    fun pullAlbums() {
        postLiveData.value = ResourceStatus.Loading()
        albumRepository.getAlbums(
            {
                postLiveData.value = ResourceStatus.Success(it)
            }, {
                postLiveData.value = ResourceStatus.Error(it)
            }
        )
    }

    fun getAlbumLiveData(): LiveData<ResourceStatus<List<Album>>> {
        return postLiveData
    }
}