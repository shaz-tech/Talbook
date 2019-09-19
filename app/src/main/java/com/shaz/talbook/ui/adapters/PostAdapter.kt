package com.shaz.talbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.R
import com.shaz.talbook.models.Post
import com.shaz.talbook.ui.listeners.ItemListener
import kotlinx.android.synthetic.main.layout_like_share_comment.view.*
import kotlinx.android.synthetic.main.layout_post_item.view.*

/**
 * Created by Shahbaz Akhtar on 17-09-2019.
 * @author Shahbaz Akhtar
 */
class PostAdapter(private val items: List<Post>, private val listener: ItemListener<Post>) :
    RecyclerView.Adapter<RootViewHolder<Post>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder<Post> {
        return PostVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_post_item,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RootViewHolder<Post>, position: Int) {
        holder.bind(items[position])
    }

    open class PostVH(itemView: View, listener: ItemListener<Post>) :
        RootViewHolder<Post>(itemView, listener) {
        private lateinit var data: Post

        init {
            itemView.comment.setOnClickListener {
                listener.onItemClick(adapterPosition, data, it)
            }
        }

        override fun bind(data: Post) {
            this.data = data
            itemView.tv_post_item_title.text = data.title
            itemView.tv_post_item_body.text = data.body
        }
    }
}