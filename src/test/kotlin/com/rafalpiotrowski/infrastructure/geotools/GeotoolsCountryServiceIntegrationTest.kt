package com.rafalpiotrowski.infrastructure.geotools

import com.rafalpiotrowski.domain.SamplePoints
import com.rafalpiotrowski.infrastructure.ResourceUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test

internal class GeotoolsCountryServiceIntegrationTest {

    companion object {

        lateinit var geotoolsCountryService: GeotoolsCountryService

        @BeforeClass @JvmStatic
        fun setUp() {
            val fileUri = ResourceUtils().getFileUri(ResourceUtils.SHP_FILE_PATH)
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
    fun shouldReturnCountryNameGivenBorderPoint() {
        // given
        val point = SamplePoints.USA_BORDER

        // when
        val result = geotoolsCountryService.getCountry(point)

        // then
        assertThat(result).contains("United States")
    }
}