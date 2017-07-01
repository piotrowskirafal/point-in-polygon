package com.rafalpiotrowski.domain

import org.assertj.core.api.Assertions.*
import org.junit.Test

class PointTest {

    @Test
    fun shouldThrowExceptionWhenLongitudeHasTooLowValue() {
        // given
        val wrongLongitude = -91.0F

        // when
        val exception = catchThrowable { Point(wrongLongitude, 70F) }

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldThrowExceptionWhenLongitudeHasTooHighValue() {
        // given
        val wrongLongitude = 91.0F

        // when
        val exception = catchThrowable { Point(wrongLongitude, 70F) }

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldThrowExceptionWhenLatitudeHasTooLowValue() {
        // given
        val wrongLatitude = -181.0F

        // when
        val exception = catchThrowable { Point(70F, wrongLatitude) }

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldThrowExceptionWhenLatitudeHasTooHighValue() {
        // given
        val wrongLatitude = 181.0F

        // when
        val exception = catchThrowable { Point(70F, wrongLatitude) }

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }
}