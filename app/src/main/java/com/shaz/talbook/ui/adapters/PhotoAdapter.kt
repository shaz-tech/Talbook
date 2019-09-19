package com.shaz.talbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shaz.talbook.R
import com.shaz.talbook.models.Photo
import com.shaz.talbook.ui.listeners.ItemListener
import com.shaz.talbook.utils.loadImage
import kotlinx.android.synthetic.main.layout_photo_item.view.*

/**
 * Created by Shahbaz Akhtar on 20-09-2019.
 * @author Shahbaz Akhtar
 */
class PhotoAdapter(
    private val requestManager: RequestManager,
    private val items: List<Photo>,
    private val listener: ItemListener<Photo>
) :
    RecyclerView.Adapter<RootViewHolder<Photo>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder<Photo> {
        return PhotoVH(
            requestManager,
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_photo_item,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RootViewHolder<Photo>, position: Int) {
        holder.bind(items[position])
    }

    open class PhotoVH(
        private var requestManager: RequestManager,
        itemView: View,
        listener: ItemListener<Photo>
    ) :
        RootViewHolder<Photo>(itemView, listener) {
        private lateinit var data: Photo

        init {
            itemView.layout_photo_item.setOnClickListener {
                listener.onItemClick(adapterPosition, data, it)
            }
        }

        override fun bind(data: Photo) {
            this.data = data
            itemView.tv_photo_item_title.text = data.title
            itemView.iv_photo_item_image.loadImage(requestManager, data.thumbnailUrl)
        }
    }
}