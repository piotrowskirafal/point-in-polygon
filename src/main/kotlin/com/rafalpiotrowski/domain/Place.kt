package com.rafalpiotrowski.domain

data class Place(val point: Point, val countryName: String) {

    override fun toString(): String {
        return "$countryName: ${point.latitude}, ${point.longitude}"
    }
}