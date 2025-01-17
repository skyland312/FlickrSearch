package com.swlee.flickrsearcher.network

import com.swlee.flickrsearcher.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getImagesByTag(@Query("tags") tag: String): FlickrResponse
}
