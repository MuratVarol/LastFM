package com.varol.lastfm.dummy

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.util.binding_adapters.SingleLiveEvent
import com.varol.lastfm.util.listener.ItemClickListener

class DummyVM : BaseVM(){

    val dummyVariable = SingleLiveEvent<Int>()

    val dummyViewEntity = MutableLiveData<List<DummyModel>>()

    val itemClickListener: ItemClickListener<DummyModel> = object
        : ItemClickListener<DummyModel> {
        override fun onItemClick(view: View, item: DummyModel, position: Int) {
            /**
             * DUMMY
             */
        }
    }
}