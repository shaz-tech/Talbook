package com.shaz.talbook.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.models.Post

/**
 * Created by Shahbaz Akhtar on 17-09-2019.
 * @author Shahbaz Akhtar
 */

abstract class RootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Post)
}