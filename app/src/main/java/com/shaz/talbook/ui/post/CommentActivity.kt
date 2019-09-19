package com.shaz.talbook.ui.post

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.gturedi.views.CustomStateOptions
import com.shaz.talbook.R
import com.shaz.talbook.models.Comment
import com.shaz.talbook.models.Post
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.ui.BaseActivity
import com.shaz.talbook.ui.adapters.CommentAdapter
import com.shaz.talbook.ui.listeners.ItemListener
import com.shaz.talbook.utils.AppConstants
import kotlinx.android.synthetic.main.activity_comment.*
import kotlin.math.abs


/**
 * Created by Shahbaz Akhtar on 18-09-2019.
 * @author Shahbaz Akhtar
 */
class CommentActivity : BaseActivity() {

    private lateinit var viewModel: CommentViewModel
    private lateinit var post: Post

    private val items = mutableListOf<Comment>()
    private lateinit var commentAdapter: CommentAdapter

    override fun bindLayout(): Int {
        return R.layout.activity_comment
    }

    override fun unbindExtras(bundle: Bundle) {
        if (bundle.containsKey(AppConstants.EXTRA_POST_DATA)) {
            post = bundle.getParcelable(AppConstants.EXTRA_POST_DATA)
            return
        }
        finish()
    }

    override fun initLayouts() {
        stateful_layout.isAnimationEnabled = true
        tv_comment_post_title.text = post.title
        tv_comment_post_body.text = post.body
        initRecyclerView()

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val openPercentage = abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
            val closePercentage = 1 - openPercentage
            tv_comment_post_name.alpha = closePercentage
            tv_comment_post_location.alpha = closePercentage
            tv_comment_post_title.alpha = closePercentage
            tv_comment_post_body.alpha = closePercentage
            toolbar.alpha = openPercentage
        })
    }

    override fun bindViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CommentViewModel::class.java)
    }

    override fun subscribeObservers() {
        viewModel.getCommentLiveData().observe(this, Observer {
            when (it) {
                is ResourceStatus.Loading ->
                    showLoading()
                is ResourceStatus.Success ->
                    it.data?.let { it1 -> updateList(it1) }
                is ResourceStatus.Error ->
                    showError(it.message)
            }
        })
        viewModel.getUserLiveData().observe(this, Observer {
            tv_comment_post_name.text = it.name
            tv_comment_post_location.text = it.address?.city
        })
        viewModel.pullComments(post.id)
        viewModel.pullUserDetails(post.userId)
    }

    private fun initRecyclerView() {
        commentAdapter = CommentAdapter(items, object : ItemListener<Comment> {
            override fun onItemClick(position: Int, data: Comment) {

            }

            override fun onItemLongClick(position: Int, data: Comment) {
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = commentAdapter
    }

    private fun updateList(posts: List<Comment>) {
        posts.run {
            items.clear()
            if (isNotEmpty()) {
                items.addAll(this)
                commentAdapter.notifyDataSetChanged()
                stateful_layout.showContent()
                return
            }
        }
        showError(getString(R.string.empty_message))
    }

    private fun showError(error: String?) {
        stateful_layout.showCustom(
            CustomStateOptions()
                .image(android.R.drawable.stat_notify_error)
                .message(error ?: getString(R.string.something_went_wrong_message))
                .buttonText(getString(R.string.retry))
                .buttonClickListener { viewModel.pullComments(post.id) }
        )
    }

    private fun showLoading() {
        stateful_layout.showCustom(
            CustomStateOptions()
                .loading().message(getString(R.string.message_comment_loading))
        )
    }

}