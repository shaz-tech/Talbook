package com.shaz.talbook.di.main

import androidx.lifecycle.ViewModel
import com.shaz.talbook.di.ViewModelKey
import com.shaz.talbook.ui.post.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindMainViewModel(viewModel: PostViewModel): ViewModel
}