package com.shaz.talbook.ui.album

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gturedi.views.CustomStateOptions
import com.shaz.talbook.R
import com.shaz.talbook.models.Album
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.ui.BaseFragment
import com.shaz.talbook.ui.adapters.AlbumAdapter
import com.shaz.talbook.ui.listeners.ItemListener
import com.shaz.talbook.ui.post.CommentActivity
import com.shaz.talbook.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album.view.*

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class AlbumFragment : BaseFragment() {

    private lateinit var viewModel: AlbumViewModel

    private val items = mutableListOf<Album>()
    private lateinit var contentAdapter: AlbumAdapter

    override fun bindLayout(): Int {
        return R.layout.fragment_album
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
                ViewModelProviders.of(this, viewModelFactory).get(AlbumViewModel::class.java)
            } ?: throw Exception("Invalid Activity")
    }

    override fun subscribeObservers() {
        viewModel.getAlbumLiveData().observe(this, Observer {
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
        contentAdapter = AlbumAdapter(items, object : ItemListener<Album> {
            override fun onItemClick(position: Int, data: Album) {
                openCommentActivity(position, data)
            }

            override fun onItemLongClick(position: Int, data: Album) {
            }

        })
        view.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.recyclerView.adapter = contentAdapter
    }

    private fun updateList(posts: List<Album>) {
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
                .buttonClickListener { viewModel.pullAlbums() }
        )
    }

    private fun openCommentActivity(position: Int, data: Album) {
        /*val intent = Intent(context, CommentActivity::class.java)
        intent.putExtra(AppConstants.EXTRA_POST_DATA, data)
        startActivity(intent)*/
    }
}