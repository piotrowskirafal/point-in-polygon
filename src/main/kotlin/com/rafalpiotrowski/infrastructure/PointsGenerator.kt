package com.rafalpiotrowski.infrastructure

import com.rafalpiotrowski.domain.Point
import org.springframework.stereotype.Service
import java.util.*

@Service
class PointsGenerator {

    fun generatePoints(pointsNumber: Int): List<Point> {
        return (1..pointsNumber)
                .map { createRandomPoint() }
                .toCollection(LinkedList())
    }

    private fun createRandomPoint(): Point {
        return Point(generateNumberBetween(Point.LATITUDE_MIN_VALUE, Point.LATITUDE_MAX_VALUE),
                generateNumberBetween(Point.LONGITUDE_MIN_VALUE, Point.LONGITUDE_MAX_VALUE))
    }

    private fun generateNumberBetween(start: Float, end: Float): Float {
        return ((Math.random() * (end - start)) + start).toFloat()
    }
}

