package com.mobile.droidsOnRoids.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.data.entity.Item
import com.mobile.droidsOnRoids.ext.snackBar
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: StartViewModel by viewModel()
    private val itemsAdapter = StartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items.observe(viewLifecycleOwner, Observer { newList ->
            itemsAdapter.submitList(newList)
        })

        recyclerView.apply {
            adapter = itemsAdapter
            val touchHelperCallback = TouchHelperCallback(itemsAdapter)
            ItemTouchHelper(touchHelperCallback).attachToRecyclerView(this)
        }

        itemsAdapter.onItemClickListener = ::showClickedItem

        shuffleItemsBtn.setOnClickListener { itemsAdapter.submitList(itemsAdapter.currentList.shuffled()) }
    }

    private fun showClickedItem(item: Item) {
        snackBar(item.title)
    }
}
