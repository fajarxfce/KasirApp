package com.fajar.template.core.data.source.remote.network

import com.fajar.template.core.data.source.remote.response.ExampleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("example")
    suspend fun getExample(): ExampleResponse
}