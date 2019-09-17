package com.shaz.talbook.di

import com.shaz.talbook.ui.post.PostActivity
import com.shaz.talbook.di.main.MainModule
import com.shaz.talbook.di.main.MainScope
import com.shaz.talbook.di.main.MainViewModelsModule
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
        modules = arrayOf(MainViewModelsModule::class, MainModule::class)
    )
    abstract fun contributeMainActivity(): PostActivity
}