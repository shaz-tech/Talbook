package com.shaz.talbook.di

import com.shaz.talbook.di.main.MainFragmentBuildersModule
import com.shaz.talbook.di.main.MainModule
import com.shaz.talbook.di.main.MainScope
import com.shaz.talbook.di.main.MainViewModelsModule
import com.shaz.talbook.ui.album.PhotoActivity
import com.shaz.talbook.ui.album.PhotoDetailedActivity
import com.shaz.talbook.ui.main.MainActivity
import com.shaz.talbook.ui.post.CommentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(
            MainViewModelsModule::class,
            MainModule::class,
            MainFragmentBuildersModule::class
        )
    )
    abstract fun contributeMainActivity(): MainActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(MainViewModelsModule::class, MainModule::class)
    )
    abstract fun contributeCommentActivity(): CommentActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(MainViewModelsModule::class, MainModule::class)
    )
    abstract fun contributePhotoActivity(): PhotoActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(MainViewModelsModule::class, MainModule::class)
    )
    abstract fun contributePhotoDetailedActivity(): PhotoDetailedActivity
}