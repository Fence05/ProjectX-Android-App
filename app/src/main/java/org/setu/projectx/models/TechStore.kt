package org.setu.androidx.models

/**
 * Data class representing a single tech store or repair shop.
 * Kotlin automatically generates toString(), equals(), hashCode(), and copy().
 */
data class TechStore(
    var id: Long = 0L,
    var name: String = "",
    var city: String = "",
    var priceLevel: Int = 1,        // 1 = cheap, 2 = median, 3 = expensive
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var note: String = "",
    var isFavorite: Boolean = false
)