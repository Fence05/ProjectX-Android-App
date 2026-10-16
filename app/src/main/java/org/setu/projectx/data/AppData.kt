package org.setu.projectx

import org.setu.androidx.models.TechStoreMemStore

object AppData {
    val TechStores = TechStoreMemStore().also { store ->
        SampleStores.all.forEach { store.create(it) }
    }
}