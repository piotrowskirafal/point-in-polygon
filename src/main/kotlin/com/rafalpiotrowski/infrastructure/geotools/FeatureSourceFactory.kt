package com.rafalpiotrowski.infrastructure.geotools

import org.geotools.data.DataStore
import org.geotools.data.DataStoreFinder
import org.geotools.data.shapefile.ShapefileDataStoreFactory
import org.geotools.data.simple.SimpleFeatureSource
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URI

@Component
class FeatureSourceFactory {

    fun createFeatureSource(shapeFileUri: URI): SimpleFeatureSource {
        val dataStore = getDataStore(shapeFileUri)
        val typeName = dataStore.typeNames[0]
        return dataStore.getFeatureSource(typeName)
    }

    @Throws(IOException::class)
    private fun getDataStore(fileUri: URI): DataStore {
        val parameters = mapOf(
                ShapefileDataStoreFactory.URLP.name to fileUri.toURL(),
                ShapefileDataStoreFactory.CREATE_SPATIAL_INDEX.name to true,
                ShapefileDataStoreFactory.ENABLE_SPATIAL_INDEX.name to true,
                ShapefileDataStoreFactory.CACHE_MEMORY_MAPS.name to true,
                ShapefileDataStoreFactory.MEMORY_MAPPED to true
        )
        return DataStoreFinder.getDataStore(parameters)
    }
}