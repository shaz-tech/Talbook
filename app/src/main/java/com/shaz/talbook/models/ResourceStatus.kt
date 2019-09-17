package com.shaz.talbook.models

/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
sealed class ResourceStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResourceStatus<T>(data)
    class Loading<T>(data: T? = null) : ResourceStatus<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceStatus<T>(data, message)
}