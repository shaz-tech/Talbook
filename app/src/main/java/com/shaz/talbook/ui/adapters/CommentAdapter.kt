package com.shaz.talbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.R
import com.shaz.talbook.models.Comment
import com.shaz.talbook.ui.listeners.ItemListener
import kotlinx.android.synthetic.main.layout_comment_item.view.*

/**
 * Created by Shahbaz Akhtar on 18-09-2019.
 * @author Shahbaz Akhtar
 */
class CommentAdapter(
    private val items: List<Comment>,
    private val listener: ItemListener<Comment>
) :
    RecyclerView.Adapter<RootViewHolder<Comment>>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootViewHolder<Comment> {
        return CommentVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_comment_item,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RootViewHolder<Comment>, position: Int) {
        holder.bind(items[position])
    }

    open class CommentVH(itemView: View, listener: ItemListener<Comment>) :
        RootViewHolder<Comment>(itemView, listener) {
        private lateinit var data: Comment

        init {
            itemView.layout_comment_item.setOnClickListener {
                listener.onItemClick(adapterPosition, data)
            }
        }

        override fun bind(data: Comment) {
            this.data = data
            itemView.tv_comment_item_name.text = data.name
            itemView.tv_comment_item_body.text = data.body
        }
    }
}