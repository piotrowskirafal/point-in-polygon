package com.rafalpiotrowski.infrastructure.config

import com.rafalpiotrowski.infrastructure.geotools.FeatureSourceFactory
import org.geotools.data.simple.SimpleFeatureSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader

@Configuration
class GeotoolsConfig {

    @Value("\${shapeFile.path}")
    lateinit var shapeFilePath: String

    @Autowired
    lateinit private var resourceLoader: ResourceLoader

    @Autowired
    lateinit var featureSourceFactory: FeatureSourceFactory

    @Bean
    fun simpleFeatureSource(): SimpleFeatureSource {
        val shapeFileUri = resourceLoader.getResource(shapeFilePath).uri
        return featureSourceFactory.createFeatureSource(shapeFileUri)
    }
}