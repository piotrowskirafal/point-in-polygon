package com.rafalpiotrowski.infrastructure

import java.net.MalformedURLException
import java.net.URI

class ResourceUtils {

    companion object {
        val SHP_FILE_PATH = "countries/ne_110m_admin_0_countries.shp"
    }

    @Throws(MalformedURLException::class)
    fun getFileUri(filePath: String): URI {
        val classLoader = this.javaClass.classLoader
        return classLoader.getResource(filePath).toURI()
    }
}