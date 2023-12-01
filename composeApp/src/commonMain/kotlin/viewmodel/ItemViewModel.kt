package viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.Item
import repository.ItemRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.asStateFlow

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _items.value = repository.getAllItems()
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
            loadItems() // Reload items to update the UI
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.updateItem(item)
            loadItems() // Reload items to update the UI
        }
    }

    fun deleteItem(id: Long?) {
        viewModelScope.launch {
            repository.deleteItem(id)
            loadItems() // Reload items to update the UI
        }
    }
}
