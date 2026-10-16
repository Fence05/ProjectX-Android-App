package org.setu.projectx

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TechStoresTest {

    @Test
    fun favorites_listIsNotNull() {
        val favorites = AppData.TechStores.findFavorites()
        assertTrue(favorites != null)
    }

    @Test
    fun toggleFavorite_changesFavoriteCount() {
        val id = AppData.TechStores.findAll().first().id
        val before = AppData.TechStores.findFavorites().size

        AppData.TechStores.toggleFavorite(id)

        val after = AppData.TechStores.findFavorites().size
        assertTrue(before != after)
    }

    @Test
    fun toggleFavorite_twice_goesBackToOriginal() {
        val id = AppData.TechStores.findAll().first().id
        val before = AppData.TechStores.findFavorites().size

        AppData.TechStores.toggleFavorite(id)
        AppData.TechStores.toggleFavorite(id)

        assertEquals(before, AppData.TechStores.findFavorites().size)
    }
}