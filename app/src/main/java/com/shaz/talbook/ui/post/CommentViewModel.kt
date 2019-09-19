package com.shaz.talbook.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaz.talbook.data.repository.PostRepository
import com.shaz.talbook.data.repository.UserRepository
import com.shaz.talbook.models.Comment
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.models.User
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 18-09-2019.
 * @author Shahbaz Akhtar
 */
class CommentViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) :
    ViewModel() {

    private val commentLiveData = MutableLiveData<ResourceStatus<List<Comment>>>()
    private val userLiveData = MutableLiveData<User>()

    fun pullComments(postId: Int) {
        commentLiveData.value = ResourceStatus.Loading()
        postRepository.getComments(
            postId,
            {
                commentLiveData.value = ResourceStatus.Success(it)
            }, {
                commentLiveData.value = ResourceStatus.Error(it)
            }
        )
    }

    fun pullUserDetails(userId: Int) {
        userRepository.getUser(
            userId,
            {
                userLiveData.value = it
            }, {
                //TODO
            }
        )
    }

    fun getCommentLiveData(): LiveData<ResourceStatus<List<Comment>>> {
        return commentLiveData
    }

    fun getUserLiveData(): LiveData<User> {
        return userLiveData
    }
}