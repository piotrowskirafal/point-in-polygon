package com.rafalpiotrowski

import com.rafalpiotrowski.domain.CountryService
import com.rafalpiotrowski.domain.Place
import com.rafalpiotrowski.infrastructure.PointsGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.lang.System.exit


@SpringBootApplication
class App : CommandLineRunner {

    @Autowired
    lateinit var countryService: CountryService

    @Autowired
    lateinit var pointsGenerator: PointsGenerator

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val points = pointsGenerator.generatePoints(1_000)
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

