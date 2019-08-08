package com.varol.lastfm.di

import com.varol.lastfm.viewmodel.AlbumsVM
import com.varol.lastfm.viewmodel.ArtistsVM
import com.varol.lastfm.viewmodel.MainVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule: Module = module {
    viewModel { MainVM() }
    viewModel { ArtistsVM(get()) }
    viewModel { AlbumsVM(get()) }
}