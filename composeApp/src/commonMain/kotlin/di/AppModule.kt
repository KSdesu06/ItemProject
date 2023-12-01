package di

import repository.ItemRepository

expect class AppModule {
    val repository: ItemRepository
}