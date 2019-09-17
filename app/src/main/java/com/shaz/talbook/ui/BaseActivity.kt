package com.shaz.talbook.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.shaz.talbook.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @LayoutRes
    protected abstract fun bindLayout(): Int
    protected abstract fun initLayouts()
    protected abstract fun bindViewModel()
    protected abstract fun subscribeObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout())
        initLayouts()
        bindViewModel()
        subscribeObservers()
    }
}