package com.shaz.talbook.ui.album

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.gturedi.views.CustomStateOptions
import com.shaz.talbook.R
import com.shaz.talbook.models.Album
import com.shaz.talbook.models.Photo
import com.shaz.talbook.models.ResourceStatus
import com.shaz.talbook.ui.BaseActivity
import com.shaz.talbook.ui.adapters.PhotoAdapter
import com.shaz.talbook.ui.listeners.ItemListener
import com.shaz.talbook.ui.utils.AutoFitGridLayoutManager
import com.shaz.talbook.utils.AppConstants
import kotlinx.android.synthetic.main.activity_comment.*
import javax.inject.Inject


/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class PhotoActivity : BaseActivity() {

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var viewModel: PhotoViewModel
    private lateinit var album: Album

    private val items = mutableListOf<Photo>()
    private lateinit var photoAdapter: PhotoAdapter

    override fun bindLayout(): Int {
        return R.layout.activity_photo
    }

    override fun unbindExtras(bundle: Bundle) {
        if (bundle.containsKey(AppConstants.EXTRA_DATA)) {
            album = bundle.getParcelable(AppConstants.EXTRA_DATA)
            return
        }
        finish()
    }

    override fun initLayouts() {
        stateful_layout.isAnimationEnabled = true
        initRecyclerView()
    }

    override fun bindViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PhotoViewModel::class.java)
    }

    override fun subscribeObservers() {
        viewModel.getPhotoLiveData().observe(this, Observer {
            when (it) {
                is ResourceStatus.Loading ->
                    showLoading()
                is ResourceStatus.Success ->
                    it.data?.let { it1 -> updateList(it1) }
                is ResourceStatus.Error ->
                    showError(it.message)
            }
        })
        album.id?.let { viewModel.pullPhotos(it) }
    }

    private fun initRecyclerView() {
        photoAdapter = PhotoAdapter(requestManager, items, object : ItemListener<Photo> {
            override fun onItemClick(position: Int, data: Photo, view: View) {
                openPhotoDetailedActivity(position, data, view)
            }

            override fun onItemLongClick(position: Int, data: Photo, view: View) {
            }

        })
        recyclerView.layoutManager = AutoFitGridLayoutManager(
            this@PhotoActivity,
            resources.getDimension(R.dimen.gallery_size).toInt()
        )
        recyclerView.adapter = photoAdapter
    }

    private fun updateList(photos: List<Photo>) {
        photos.run {
            items.clear()
            if (isNotEmpty()) {
                items.addAll(this)
                photoAdapter.notifyDataSetChanged()
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
                .buttonClickListener { album.id?.let { it1 -> viewModel.pullPhotos(it1) } }
        )
    }

    private fun showLoading() {
        stateful_layout.showCustom(
            CustomStateOptions()
                .loading().message(getString(R.string.message_photo_loading))
        )
    }

    private fun openPhotoDetailedActivity(position: Int, data: Photo, view: View) {
        val intent = Intent(this@PhotoActivity, PhotoDetailedActivity::class.java)
        intent.putExtra(AppConstants.EXTRA_DATA, data)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "galleryImage")
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

}