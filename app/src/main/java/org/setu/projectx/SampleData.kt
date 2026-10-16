package org.setu.projectx

import org.setu.androidx.models.TechStore

object SampleStores {
    val all = listOf(
        TechStore(name = "PixelPoint", city = "Dublin", priceLevel = 2, latitude = 53.350, longitude = -6.260),
        TechStore(name = "FixIt Fast", city = "Cork", priceLevel = 1, latitude = 51.897, longitude = -8.470),
        TechStore(name = "Circuit Hub", city = "Galway", priceLevel = 3, latitude = 53.270, longitude = -9.057)
        // add up to 10
    )
}