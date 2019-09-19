package com.shaz.talbook.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shaz.talbook.ui.listeners.ItemListener

/**
 * Created by Shahbaz Akhtar on 17-09-2019.
 * @author Shahbaz Akhtar
 */

abstract class RootViewHolder<E>(itemView: View, listener: ItemListener<E>) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: E)
}