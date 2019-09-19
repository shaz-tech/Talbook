package com.shaz.talbook.ui.listeners

/**
 * Created by Shahbaz Akhtar on 18-09-2019.
 * @author Shahbaz Akhtar
 */
interface ItemListener<E> {
    fun onItemClick(position: Int, data: E)
    fun onItemLongClick(position: Int, data: E)
}