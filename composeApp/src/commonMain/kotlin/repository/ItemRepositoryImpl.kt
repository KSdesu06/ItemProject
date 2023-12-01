package repository

import com.example.kat.db.Database
import com.example.kat.db.Item_table
import model.Item

class ItemRepositoryImpl(private val database: Database) : ItemRepository {

    private val dbQueries = database.itemQueries

    override suspend fun getAllItems(): List<Item> {
        return dbQueries.selectAll().executeAsList().map { it.toItem() }
    }

    override suspend fun insertItem(item: Item) {
        dbQueries.insertItem(name = item.name, description = item.description)
    }

    override suspend fun updateItem(item: Item) {
        item.id?.let { dbQueries.updateItem(id = it, name = item.name, description = item.description) }
    }

    override suspend fun deleteItem(id: Long?) {
        if (id != null) {
            dbQueries.deleteItem(id)
        }
    }

    private fun Item_table.toItem(): Item {
        return Item(id = this.id, name = this.name, description = this.description)
    }
}
