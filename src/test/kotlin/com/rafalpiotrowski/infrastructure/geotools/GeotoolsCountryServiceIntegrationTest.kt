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
        val point = SamplePoints.WARSAW_POLAND

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("Poland")
    }

    @Test
    fun shouldNotReturnCountryNameGivenPointInTheOcean() {
        // given
        val point = SamplePoints.ATLANTIC_OCEAN

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).isEmpty
    }

    @Test
    fun shouldReturnCountryNameGivenPointFromCountryInsideAnotherCountry() {
        // given
        val point = SamplePoints.MASERU_LESOTHO

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("Lesotho")
    }

    @Test
    fun shouldReturnAntarctica() {
        // given
        val point = SamplePoints.ANTARCTICA

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("Antarctica")
    }

    @Test
    fun shouldReturnFranceGivenPointInAntarctica() {
        // given
        val point = SamplePoints.FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("France")
    }

    @Test
    fun shouldReturnCountryNameGivenBorderPoint() {
        // given
        val point = SamplePoints.USA_BORDER

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("United States")
    }
}