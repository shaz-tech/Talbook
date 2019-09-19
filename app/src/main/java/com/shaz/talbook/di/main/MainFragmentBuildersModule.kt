package com.shaz.talbook.di.main

import com.shaz.talbook.ui.album.AlbumFragment
import com.shaz.talbook.ui.post.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Shahbaz Akhtar on 19-09-2019.
 * @author Shahbaz Akhtar
 */
@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributePostFragment(): PostFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAlbumFragment(): AlbumFragment
}