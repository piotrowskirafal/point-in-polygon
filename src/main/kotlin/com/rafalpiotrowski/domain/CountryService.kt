package com.rafalpiotrowski.domain

import java.util.*

interface CountryService {

    fun getCountry(point: Point): Optional<String>
}
