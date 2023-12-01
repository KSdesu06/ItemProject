package di

import android.content.Context
import com.example.kat.db.Database
import driver.DatabaseDriverFactory
import repository.ItemRepository
import repository.ItemRepositoryImpl

actual class AppModule(private val context: Context) {
    actual val repository: ItemRepository by lazy {
        ItemRepositoryImpl(
            database = Database(
                driver = DatabaseDriverFactory(context).createDriver()
            )
        )
    }
}