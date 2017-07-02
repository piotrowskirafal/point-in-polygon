package com.rafalpiotrowski.infrastructure.geotools

import com.rafalpiotrowski.domain.CountryService
import com.rafalpiotrowski.domain.Point
import org.geotools.data.simple.SimpleFeatureSource
import org.geotools.factory.CommonFactoryFinder
import org.geotools.geometry.DirectPosition2D
import org.geotools.geometry.jts.JTS
import org.opengis.feature.simple.SimpleFeature
import org.opengis.filter.Filter
import java.util.*

class GeotoolsCountryService(private val simpleFeatureSource: SimpleFeatureSource) : CountryService {

    override fun getCountry(point: Point): Optional<String> {
        val filter = createPointInPolygonFilter(getLocalName(simpleFeatureSource), point)
        val result = simpleFeatureSource.getFeatures(filter)
        result.features().use { features ->
            if (features.hasNext()) {
                return Optional.of(getCountryName(features.next()))
            }
        }
        return Optional.empty()
    }

    private fun createPointInPolygonFilter(geometryAttributeName: String, point: Point): Filter {
        val filterFactory = CommonFactoryFinder.getFilterFactory2(null)
        val jstPoint = JTS.toGeometry(DirectPosition2D(point.longitude.toDouble(), point.latitude.toDouble()))
        return filterFactory.contains(filterFactory.property(geometryAttributeName), filterFactory.literal(jstPoint))
    }

    private fun getLocalName(source: SimpleFeatureSource): String {
        return source.schema.geometryDescriptor.localName
    }

    private fun getCountryName(feature: SimpleFeature): String {
        return feature.getAttribute("name_long").toString()
    }
}