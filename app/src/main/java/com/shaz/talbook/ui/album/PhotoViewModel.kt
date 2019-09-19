package com.shaz.talbook.ui.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaz.talbook.data.repository.AlbumRepository
import com.shaz.talbook.models.Photo
import com.shaz.talbook.models.ResourceStatus
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 20-09-2019.
 * @author Shahbaz Akhtar
 */
class PhotoViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {

    private val photoLiveData = MutableLiveData<ResourceStatus<List<Photo>>>()

    fun pullPhotos(albumId: Int) {
        photoLiveData.value = ResourceStatus.Loading()
        albumRepository.getPhotos(
            albumId,
            {
                photoLiveData.value = ResourceStatus.Success(it)
            }, {
                photoLiveData.value = ResourceStatus.Error(it)
            }
        )
    }

    fun getPhotoLiveData(): LiveData<ResourceStatus<List<Photo>>> {
        return photoLiveData
    }
}