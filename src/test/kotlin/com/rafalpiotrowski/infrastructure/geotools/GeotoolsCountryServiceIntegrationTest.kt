package com.rafalpiotrowski.infrastructure.geotools

import com.rafalpiotrowski.domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test
import org.springframework.util.ResourceUtils

internal class GeotoolsCountryServiceIntegrationTest {

    companion object {

        lateinit var geotoolsCountryService: GeotoolsCountryService

        @BeforeClass @JvmStatic
        fun setUp() {
            val fileUri = ResourceUtils.getURL("classpath:countries/ne_110m_admin_0_countries.shp").toURI()
            val featureSourceFactory = FeatureSourceFactory()
            geotoolsCountryService = GeotoolsCountryService(featureSourceFactory.createFeatureSource(fileUri))
        }
    }

    @Test
    fun shouldReturnCountryNameGivenSimplePoint() {
        // given
        val point = Point(50.036999, 18.391258)

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("Poland")
    }

    @Test
    fun shouldNotReturnCountryNameGivenPointInTheOcean() {
        // given
        val point = Point(38.073558, -40.783570)

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).isEmpty
    }

    @Test
    fun shouldReturnAntarctica() {
        // given
        val point = Point(-76.746264, 12.224188)

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("Antarctica")
    }

    @Test
    fun shouldReturnFrance() {
        // given
        val point = Point(-49.289710, 69.199114)

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("France")
    }
}