package com.shaz.talbook.ui.album

import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.shaz.talbook.R
import com.shaz.talbook.models.Photo
import com.shaz.talbook.ui.BaseActivity
import com.shaz.talbook.utils.AppConstants
import com.shaz.talbook.utils.loadImage
import kotlinx.android.synthetic.main.activity_photo_detailed.*
import javax.inject.Inject


/**
 * Created by Shahbaz Akhtar on 20-09-2019.
 * @author Shahbaz Akhtar
 */
class PhotoDetailedActivity : BaseActivity() {

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var photo: Photo

    override fun bindLayout(): Int {
        return R.layout.activity_photo_detailed
    }

    override fun unbindExtras(bundle: Bundle) {
        if (bundle.containsKey(AppConstants.EXTRA_DATA)) {
            photo = bundle.getParcelable(AppConstants.EXTRA_DATA)
            return
        }
        finish()
    }

    override fun initLayouts() {
        iv_photo_detailed_image.loadImage(requestManager, photo.url)
        btn_close.setOnClickListener {
            onBackPressed()
        }
    }

    override fun bindViewModel() {

    }

    override fun subscribeObservers() {

    }

}