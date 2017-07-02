package com.rafalpiotrowski

import com.rafalpiotrowski.domain.Place
import com.rafalpiotrowski.infrastructure.PointsGenerator
import com.rafalpiotrowski.infrastructure.ResourceUtils
import com.rafalpiotrowski.infrastructure.geotools.FeatureSourceFactory
import com.rafalpiotrowski.infrastructure.geotools.GeotoolsCountryService
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    System.setProperty("org.geotools.shapefile.maxQixCacheSize", "1000")
    val fileUri = ResourceUtils().getFileUri(ResourceUtils.SHP_FILE_PATH)
    val featureSourceFactory = FeatureSourceFactory()
    val countryService = GeotoolsCountryService(featureSourceFactory.createFeatureSource(fileUri))
    val pointsGenerator = PointsGenerator()
    val points = pointsGenerator.generatePoints(1000000)

    val start = System.nanoTime()
    val places = points.map { point -> Place(point, countryService.getCountry(point).orElseGet { "Not in a country" }) }
    val time = System.nanoTime() - start
    places.forEach({ place -> println(place) })
    println("time: " + TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS))
}