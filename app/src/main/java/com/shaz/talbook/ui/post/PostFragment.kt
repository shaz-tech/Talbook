package com.shaz.talbook.ui.post

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gturedi.views.CustomStateOptions
import com.shaz.talbook.R
import com.shaz.talbook.models.Post
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.ui.BaseFragment
import com.shaz.talbook.ui.adapters.PostAdapter
import com.shaz.talbook.ui.listeners.ItemListener
import com.shaz.talbook.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.view.*

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class PostFragment : BaseFragment() {

    private lateinit var viewModel: PostViewModel

    private val items = mutableListOf<Post>()
    private lateinit var contentAdapter: PostAdapter

    override fun bindLayout(): Int {
        return R.layout.fragment_post
    }

    override fun unbindArguments(bundle: Bundle) {

    }

    override fun initLayouts(view: View): View {
        view.stateful_layout.isAnimationEnabled = true
        initRecyclerView(view)
        return view
    }

    override fun bindViewModel() {
        viewModel =
            activity?.run {
                ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)
            } ?: throw Exception("Invalid Activity")
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

    private fun initRecyclerView(view: View) {
        contentAdapter = PostAdapter(items, object : ItemListener<Post> {
            override fun onItemClick(position: Int, data: Post) {
                openCommentActivity(position, data)
            }

            override fun onItemLongClick(position: Int, data: Post) {
            }

        })
        view.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.recyclerView.adapter = contentAdapter
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

    private fun openCommentActivity(position: Int, data: Post) {
        val intent = Intent(context, CommentActivity::class.java)
        intent.putExtra(AppConstants.EXTRA_POST_DATA, data)
        startActivity(intent)
    }
}