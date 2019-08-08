package com.varol.lastfm.di

import com.varol.lastfm.usecase.SearchArtistUseCase
import com.varol.lastfm.usecase.TopAlbumsUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { SearchArtistUseCase(get()) }
    single { TopAlbumsUseCase(get()) }

}