package com.shaz.talbook.di.main

import androidx.lifecycle.ViewModel
import com.shaz.talbook.di.ViewModelKey
import com.shaz.talbook.ui.album.AlbumViewModel
import com.shaz.talbook.ui.album.PhotoViewModel
import com.shaz.talbook.ui.post.CommentViewModel
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
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentViewModel::class)
    abstract fun bindCommentViewModel(viewModel: CommentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    abstract fun bindAlbumViewModel(viewModel: AlbumViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    abstract fun bindPhotoViewModel(viewModel: PhotoViewModel): ViewModel
}