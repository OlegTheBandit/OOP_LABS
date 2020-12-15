package com.example.hellowworld

import Shape
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MoveItemAdapter(var adapter: RecyclerViewAdapter, var list: ArrayList<Shape>): ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val sourcePosition = viewHolder.adapterPosition
        val targetPosition = target.adapterPosition
        Collections.swap(list, sourcePosition, targetPosition)
        adapter.notifyItemMoved(sourcePosition, targetPosition)
        return true

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var pos = viewHolder.adapterPosition
        adapter.deleteItem(pos)

    }

}


