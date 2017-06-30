package com.rafalpiotrowski.infrastructure

import com.rafalpiotrowski.domain.Point
import org.springframework.stereotype.Service
import java.util.*

@Service
class PointsGenerator {

    fun generatePoints(pointsNumber: Long): List<Point> {
        return (1..pointsNumber)
                .map { createRandomPoint() }
                .toCollection(LinkedList())
    }

    private fun createRandomPoint(): Point {
        return Point(generateDoubleBetween(Point.LATITUDE_MIN_VALUE, Point.LATITUDE_MAX_VALUE),
                generateDoubleBetween(Point.LONGITUDE_MIN_VALUE, Point.LONGITUDE_MAX_VALUE))
    }

    private fun generateDoubleBetween(start: Int, end: Int): Double {
        return (Math.random() * (end - start)) + start
    }
}

