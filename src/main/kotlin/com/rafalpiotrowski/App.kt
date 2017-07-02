package com.rafalpiotrowski

import com.rafalpiotrowski.domain.CountryService
import com.rafalpiotrowski.domain.Place
import com.rafalpiotrowski.infrastructure.PointsGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.lang.System.exit
import java.util.concurrent.TimeUnit

@SpringBootApplication
class App(private val countryService: CountryService, private val pointsGenerator: PointsGenerator,
          @Value("\${points.number}") private val pointsNumber: Int) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        println("generating points ($pointsNumber)")
        val points = pointsGenerator.generatePoints(pointsNumber)
        println("matching points with countries")
        val start = System.nanoTime()
        val places = points.map { point -> Place(point, countryService.getCountry(point).orElseGet { "Not in a country" }) }
        val time = System.nanoTime() - start
        places.forEach({ place -> println(place) })
        println("time: " + TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS))
        exit(0)
    }
}

fun main(args: Array<String>) {
    val app = SpringApplication(App::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}
