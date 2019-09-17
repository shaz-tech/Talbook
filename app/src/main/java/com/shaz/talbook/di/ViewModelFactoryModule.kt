package com.shaz.talbook.di

import androidx.lifecycle.ViewModelProvider
import com.shaz.talbook.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Shahbaz Akhtar on 16-09-2019.
 * @author Shahbaz Akhtar
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}