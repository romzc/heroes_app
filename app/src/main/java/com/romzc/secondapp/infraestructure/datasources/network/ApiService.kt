package com.romzc.secondapp.infraestructure.datasources.network

import com.romzc.secondapp.infraestructure.models.SuperHeroItemResponse
import com.romzc.secondapp.infraestructure.models.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/2147185955471543/search/{name}")
    suspend fun getSuperheros(@Path("name") heroName: String): Response<SuperHeroResponse>

    @GET("api/2147185955471543/{id}")
    suspend fun getSuperHeroDetail(@Path("id") heroId: String): Response<SuperHeroItemResponse>
}