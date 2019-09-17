package com.shaz.talbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.R
import com.shaz.talbook.models.Post
import kotlinx.android.synthetic.main.layout_post_item.view.*

/**
 * Created by Shahbaz Akhtar on 17-09-2019.
 * @author Shahbaz Akhtar
 */
class PostAdapter(private val items: List<Post>) :
    RecyclerView.Adapter<RootViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder {
        return PostVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_post_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RootViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class PostVH(itemView: View) : RootViewHolder(itemView) {
        override fun bind(data: Post) {
            itemView.tv_post_item_title.text = data.title
            itemView.tv_post_item_body.text = data.body
        }
    }
}