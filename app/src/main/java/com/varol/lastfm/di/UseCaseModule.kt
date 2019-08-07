package com.varol.lastfm.di

import com.varol.lastfm.usecase.SearchArtistUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { SearchArtistUseCase(get()) }

}