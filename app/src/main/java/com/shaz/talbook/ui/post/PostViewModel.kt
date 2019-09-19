package com.shaz.talbook.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaz.talbook.data.repository.PostRepository
import com.shaz.talbook.models.Post
import com.shaz.talbook.models.ResourceStatus
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    private val postLiveData = MutableLiveData<ResourceStatus<List<Post>>>()

    init {
        print("PostViewModel initialized")
        pullPosts()
    }

    fun pullPosts() {
        postLiveData.value = ResourceStatus.Loading()
        postRepository.getPosts(
            {
                postLiveData.value = ResourceStatus.Success(it)
            }, {
                postLiveData.value = ResourceStatus.Error(it)
            }
        )
    }

    fun getPostLiveData(): LiveData<ResourceStatus<List<Post>>> {
        return postLiveData
    }
}