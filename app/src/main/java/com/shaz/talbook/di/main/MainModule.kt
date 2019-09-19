package com.shaz.talbook.di.main

import com.shaz.talbook.data.remote.MainApiService
import com.shaz.talbook.data.remote.UserApiService
import com.shaz.talbook.data.repository.AlbumRepository
import com.shaz.talbook.data.repository.PostRepository
import com.shaz.talbook.data.repository.UserRepository
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
    fun provideMainApiService(retrofitBuilder: Retrofit.Builder): MainApiService {
        return retrofitBuilder
            .build()
            .create(MainApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideUserApiService(retrofitBuilder: Retrofit.Builder): UserApiService {
        return retrofitBuilder
            .build()
            .create(UserApiService::class.java)
    }

    @MainScope
    @Provides
    fun providePostRepository(
        mainApiService: MainApiService
    ): PostRepository {
        return PostRepository(mainApiService)
    }

    @MainScope
    @Provides
    fun provideAlbumRepository(
        mainApiService: MainApiService
    ): AlbumRepository {
        return AlbumRepository(mainApiService)
    }

    @MainScope
    @Provides
    fun provideUserRepository(
        userApiService: UserApiService
    ): UserRepository {
        return UserRepository(userApiService)
    }
}