package com.rafalpiotrowski.infrastructure.geotools

import com.rafalpiotrowski.domain.Point
import com.rafalpiotrowski.infrastructure.PointsGenerator
import org.assertj.core.api.Assertions.*
import org.junit.Test

internal class PointsGeneratorTest {

    private val pointsGenerator = PointsGenerator()

    @Test
    fun shouldGeneratePointWithCorrectLatitudeAndLongitude() {
        // given
        val numberOfPoints = 1L

        // when
        val result = pointsGenerator.generatePoints(numberOfPoints)[0]

        // then
        assertThat(result.latitude).isBetween(Point.LATITUDE_MIN_VALUE, Point.LATITUDE_MAX_VALUE)
        assertThat(result.longitude).isBetween(Point.LONGITUDE_MIN_VALUE, Point.LONGITUDE_MAX_VALUE)
    }

    @Test
    fun shouldGenerateGivenNumberOfPoints() {
        // given
        val numberOfPoints = 1_000_000L

        // when
        val result = pointsGenerator.generatePoints(numberOfPoints)

        // then
        assertThat(result.size.toLong()).isEqualTo(numberOfPoints)
    }
}