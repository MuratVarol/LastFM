package com.varol.lastfm.util.binding_adapters

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.varol.lastfm.base.AdapterBuilder
import com.varol.lastfm.base.BaseRecyclerAdapter
import com.varol.lastfm.util.listener.ItemClickListener


@BindingAdapter(
    value = [
        "itemList",
        "itemLayoutId",
        "dividerEnabled",
        "itemClickListener",
        "dividerDrawableId",
        "dividerHorizontalDrawableId"
    ],
    requireAll = false
)
fun bindRecyclerView(
    recyclerView: RecyclerView,
    itemList: List<Nothing>?,
    itemLayoutId: Int,
    dividerEnabled: Boolean,
    itemClickListener: ItemClickListener<Nothing>?,
    dividerDrawableId: Drawable?,
    dividerHorizontalDrawableId: Drawable?
) {
    if (itemList == null) return
    clearDecorations(recyclerView)

    if (recyclerView.adapter == null) {
        val adapter = createAdapter(itemList, itemLayoutId, itemClickListener)
        setDefaultLayoutManager(recyclerView)
        recyclerView.adapter = adapter
    } else {
        val adapter = recyclerView.adapter as BaseRecyclerAdapter<*>
        adapter.updateData(itemList)
    }
    showDefaultDivider(dividerEnabled, recyclerView)
    dividerDrawableId?.let { addDividerDrawable(dividerDrawableId, recyclerView) }
    dividerHorizontalDrawableId?.let {
        addHorizontalDividerDrawable(
            dividerDrawableId,
            recyclerView
        )
    }
}

private fun setDefaultLayoutManager(recyclerView: RecyclerView) {
    if (recyclerView.layoutManager != null) return
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
}

private fun addSnapHelper(recyclerView: RecyclerView, snapEnabled: Boolean) {
    if (!snapEnabled) return
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(recyclerView)
}

private fun showDefaultDivider(dividerEnabled: Boolean, recyclerView: RecyclerView) {
    if (!dividerEnabled) return
    val dividerItemDecoration = DividerItemDecoration(
        recyclerView.context,
        RecyclerView.VERTICAL
    )
    recyclerView.addItemDecoration(dividerItemDecoration)
}

private fun addDividerDrawable(dividerDrawable: Drawable?, recyclerView: RecyclerView) {
    if (dividerDrawable == null) return

    getOrientation(recyclerView)?.let {

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            RecyclerView.VERTICAL
        )
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}

private fun addHorizontalDividerDrawable(dividerDrawable: Drawable?, recyclerView: RecyclerView) {
    if (dividerDrawable == null) return

    getOrientation(recyclerView)?.let {

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            RecyclerView.HORIZONTAL
        )
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}

private fun getOrientation(parent: RecyclerView): Int? {
    return if (parent.layoutManager is LinearLayoutManager) {
        val layoutManager = parent.layoutManager as LinearLayoutManager?
        layoutManager?.orientation ?: 0
    } else
        null
}

private fun clearDecorations(recyclerView: RecyclerView) {
    val decorationCount: Int = recyclerView.itemDecorationCount
    try {
        for (i in 0..decorationCount) {
            recyclerView.removeItemDecorationAt(i)
        }
    } catch (e: Exception) {
        return
    }
}

private fun createAdapter(
    modelList: List<Nothing>,
    layoutId: Int,
    itemClickListener: ItemClickListener<Nothing>?
): BaseRecyclerAdapter<Nothing> {
    return AdapterBuilder(
        modelList,
        layoutId,
        itemClickListener
    ).build()
}
