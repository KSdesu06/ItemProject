package di

import com.example.kat.db.Database
import driver.DatabaseDriverFactory
import repository.ItemRepository
import repository.ItemRepositoryImpl

actual class AppModule {
    actual val repository: ItemRepository by lazy {
        ItemRepositoryImpl(
            database = Database(
                driver = DatabaseDriverFactory().createDriver()
            )
        )
    }
}