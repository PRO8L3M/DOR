package com.mobile.droidsOnRoids.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.droidsOnRoids.data.entity.Item
import com.mobile.droidsOnRoids.repository.Repository

class StartViewModel(private val repository: Repository) : ViewModel() {

    private val _items: MutableLiveData<List<Item>> by lazy { MutableLiveData<List<Item>>() }
    val items: LiveData<List<Item>> = _items

    init {
        refreshData()
    }

    private fun refreshData() {
        _items.value = List(30) { index ->
            val id = index + 1
            Item(id = id, title = "Title $id", description = "Blah blah")
        }
    }
}