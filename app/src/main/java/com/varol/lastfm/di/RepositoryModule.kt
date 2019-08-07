package com.varol.lastfm.di

import com.varol.lastfm.data.remote.repository.ArtistRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule: Module = module {
    single { ArtistRepository(get()) }
}
