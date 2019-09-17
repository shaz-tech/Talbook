package com.shaz.talbook.ui.post

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gturedi.views.CustomStateOptions
import com.shaz.talbook.R
import com.shaz.talbook.models.Post
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.ui.BaseActivity
import com.shaz.talbook.ui.adapters.PostAdapter
import kotlinx.android.synthetic.main.activity_post.*

/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
class PostActivity : BaseActivity() {

    private lateinit var viewModel: PostViewModel

    private val items = mutableListOf<Post>()
    private lateinit var contentAdapter: PostAdapter

    override fun bindLayout(): Int {
        return R.layout.activity_post
    }

    override fun initLayouts() {
        stateful_layout.isAnimationEnabled = true
        initRecyclerView()
    }

    override fun bindViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)
    }

    override fun subscribeObservers() {
        viewModel.getPostLiveData().observe(this, Observer {
            when (it) {
                is ResourceStatus.Loading ->
                    stateful_layout.showLoading()
                is ResourceStatus.Success ->
                    it.data?.let { it1 -> updateList(it1) }
                is ResourceStatus.Error ->
                    showError(it.message)
            }
        })
    }

    private fun initRecyclerView() {
        contentAdapter = PostAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = contentAdapter
    }

    private fun updateList(posts: List<Post>) {
        posts.run {
            items.clear()
            if (isNotEmpty()) {
                items.addAll(this)
                contentAdapter.notifyDataSetChanged()
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
                .buttonClickListener { viewModel.pullPosts() }
        )
    }
}