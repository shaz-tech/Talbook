package com.shaz.talbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.R
import com.shaz.talbook.models.Album
import com.shaz.talbook.ui.listeners.ItemListener
import kotlinx.android.synthetic.main.layout_album_item.view.*

/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
class AlbumAdapter(private val items: List<Album>, private val listener: ItemListener<Album>) :
    RecyclerView.Adapter<RootViewHolder<Album>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder<Album> {
        return AlbumVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_album_item,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RootViewHolder<Album>, position: Int) {
        holder.bind(items[position])
    }

    open class AlbumVH(itemView: View, listener: ItemListener<Album>) :
        RootViewHolder<Album>(itemView, listener) {
        private lateinit var data: Album

        init {
            itemView.layout_album_item.setOnClickListener {
                listener.onItemClick(adapterPosition, data, it)
            }
        }

        override fun bind(data: Album) {
            this.data = data
            itemView.tv_album_item_title.text = data.title
        }
    }
}