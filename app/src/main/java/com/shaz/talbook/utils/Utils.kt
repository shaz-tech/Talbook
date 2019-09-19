package com.shaz.talbook.utils

import android.app.Activity
import android.util.DisplayMetrics


/**
 * Created by Shahbaz Akhtar on 20-09-2019.
 * @author Shahbaz Akhtar
 */
public fun convertDPToPixels(activity: Activity, dp: Int): Float {
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    val logicalDensity = metrics.density
    return dp * logicalDensity
}