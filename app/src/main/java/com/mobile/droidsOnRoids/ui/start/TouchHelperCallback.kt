package com.mobile.droidsOnRoids.ui.start

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TouchHelperCallback<T, VH: RecyclerView.ViewHolder>(private val adapter: ListAdapter<T, VH>) : SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.START or ItemTouchHelper.END) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = true

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        val newList = adapter.currentList.toMutableList()
        val movedItem = newList[fromPos]
        val targetItem = newList[toPos]
        newList[fromPos] = targetItem
        newList[toPos] = movedItem
        adapter.submitList(newList)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val newList = adapter.currentList.toMutableList()
        newList.removeAt(viewHolder.adapterPosition)
        adapter.submitList(newList)
    }
}