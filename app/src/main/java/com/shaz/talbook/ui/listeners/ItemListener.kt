package com.shaz.talbook.ui.listeners

import android.view.View

/**
 * Created by Shahbaz Akhtar on 18-09-2019.
 * @author Shahbaz Akhtar
 */
interface ItemListener<E> {
    fun onItemClick(position: Int, data: E, view: View)
    fun onItemLongClick(position: Int, data: E, view: View)
}