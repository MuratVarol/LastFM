package com.varol.lastfm.ui

import android.os.Bundle
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseActivity
import com.varol.lastfm.databinding.ActivityMainBinding
import com.varol.lastfm.viewmodel.MainVM

class MainActivity : BaseActivity<MainVM, ActivityMainBinding>(MainVM::class) {

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loadFragment(R.id.container_main, StoredAlbumsFragment(), false)

    }
}
