package org.setu.androidx.models

import java.util.concurrent.atomic.AtomicLong

class TechStoreMemStore {
    private val stores = ArrayList<TechStore>()
    private val lastId = AtomicLong(0L)

    fun findAll(): List<TechStore> {
        return stores
    }

    fun findFavorites(): List<TechStore> {
        return stores.filter { s -> s.isFavorite }
    }

    fun create(store: TechStore) {
        store.id = lastId.incrementAndGet()
        stores.add(store)
    }

    fun update(store: TechStore): Boolean {
        val foundStore = findOne(store.id)
        return if (foundStore != null) {
            foundStore.name = store.name
            foundStore.city = store.city
            foundStore.priceLevel = store.priceLevel
            foundStore.latitude = store.latitude
            foundStore.longitude = store.longitude
            foundStore.note = store.note
            foundStore.isFavorite = store.isFavorite
            true
        } else {
            false
        }
    }

    fun delete(id: Long): Boolean {
        val foundStore = findOne(id)
        return if (foundStore != null) {
            stores.remove(foundStore)
            true
        } else {
            false
        }
    }

    fun findOne(id: Long): TechStore? {
        return stores.find { s -> s.id == id }
    }

    fun toggleFavorite(id: Long): Boolean {
        val foundStore = findOne(id)
        return if (foundStore != null) {
            foundStore.isFavorite = !foundStore.isFavorite
            true
        } else {
            false
        }
    }
}