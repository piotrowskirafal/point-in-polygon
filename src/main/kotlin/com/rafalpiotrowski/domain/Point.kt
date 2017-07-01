package com.rafalpiotrowski.domain

data class Point(val latitude: Float, val longitude: Float) {
    companion object {
        const val LATITUDE_MIN_VALUE: Float = -90.0F
        const val LATITUDE_MAX_VALUE: Float = 90.0F
        const val LONGITUDE_MIN_VALUE: Float = -180.0F
        const val LONGITUDE_MAX_VALUE: Float = 180.0F
    }

    init {
        assertThatCoordinateIsBetween(latitude, LATITUDE_MIN_VALUE, LATITUDE_MAX_VALUE)
        assertThatCoordinateIsBetween(longitude, LONGITUDE_MIN_VALUE, LONGITUDE_MAX_VALUE)
    }

    private fun assertThatCoordinateIsBetween(coordinate: Float, minValue: Float, maxValue: Float) {
        if (coordinate < minValue || coordinate > maxValue) {
            throw IllegalArgumentException("Coordinate must be between $minValue and $maxValue")
        }
    }
}