package repository

import model.Item

interface ItemRepository {
    suspend fun getAllItems(): List<Item>
    suspend fun insertItem(item: Item)
    suspend fun updateItem(item: Item)
    suspend fun deleteItem(id: Long?)
}
