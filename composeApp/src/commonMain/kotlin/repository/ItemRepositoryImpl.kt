package repository

import com.example.kat.db.Database
import com.example.kat.db.Item_table
import model.Item

class ItemRepositoryImpl(private val database: Database) : ItemRepository {

    override suspend fun getAllItems(): List<Item> {
        return database.itemQueries.selectAll().executeAsList().map { it.toItem() }
    }

    override suspend fun insertItem(item: Item) {
        database.itemQueries.insertItem(name = item.name, description = item.description)
    }

    override suspend fun updateItem(item: Item) {
        item.id?.let { database.itemQueries.updateItem(id = it, name = item.name, description = item.description) }
    }

    override suspend fun deleteItem(id: Long?) {
        if (id != null) {
            database.itemQueries.deleteItem(id)
        }
    }

    private fun Item_table.toItem(): Item {
        return Item(id = this.id, name = this.name, description = this.description)
    }
}
