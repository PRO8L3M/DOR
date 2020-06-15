package com.mobile.droidsOnRoids.ui.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.data.entity.Item
import com.mobile.droidsOnRoids.ext.getDiffUtilCallback
import kotlinx.android.synthetic.main.item_view.view.*

class StartAdapter :
    ListAdapter<Item, StartViewHolder>(getDiffUtilCallback { oldItem, newItem -> oldItem.id == newItem.id }) {

    var onItemClickListener: ((Item) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return StartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClickListener)
}

class StartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Item, onItemClickListener: ((Item) -> Unit)) = with(itemView) {
        item_view_title.text = item.title
        item_view_description.text = item.description
        setOnClickListener {
            onItemClickListener(item)
        }
    }
}