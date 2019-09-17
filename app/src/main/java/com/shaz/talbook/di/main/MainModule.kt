package com.shaz.talbook.di.main

import com.shaz.talbook.data.remote.PostApiService
import com.shaz.talbook.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApiService(retrofitBuilder: Retrofit.Builder): PostApiService {
        return retrofitBuilder
            .build()
            .create(PostApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideCreateBlogRepository(
        postApiService: PostApiService
    ): PostRepository {
        return PostRepository(postApiService)
    }
}