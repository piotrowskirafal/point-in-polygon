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

@SpringBootApplication
class App(private val countryService: CountryService, private val pointsGenerator: PointsGenerator,
          @Value("\${points.number}") private val pointsNumber: Int) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val points = pointsGenerator.generatePoints(pointsNumber)
        val places = points.map { point -> Place(point, countryService.getCountry(point).orElseGet { "not_in_a_country" }) }
        places.forEach({ place -> println(place) })
        exit(0)
    }
}

fun main(args: Array<String>) {
    val app = SpringApplication(App::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}

