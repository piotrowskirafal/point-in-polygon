package com.rafalpiotrowski.domain

data class Point(val latitude: Double, val longitude: Double) {
    companion object {
        const val LATITUDE_MIN_VALUE = -90
        const val LATITUDE_MAX_VALUE = 90
        const val LONGITUDE_MIN_VALUE = -180
        const val LONGITUDE_MAX_VALUE = 180
    }
}