package com.romzc.secondapp.di

import com.romzc.secondapp.domain.datasources.SuperHeroDatasource
import com.romzc.secondapp.domain.repositories.SuperHeroRepository
import com.romzc.secondapp.infraestructure.datasources.network.ApiService
import com.romzc.secondapp.infraestructure.datasources.network.NetworkDataSource
import com.romzc.secondapp.infraestructure.repositories.SuperHeroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getNetworkDataSource(apiService: ApiService) : SuperHeroDatasource {
        return NetworkDataSource(apiService)
    }

    @Provides
    @Singleton
    fun getSuperHeroRepository(superHeroSource: SuperHeroDatasource) : SuperHeroRepository {
        return SuperHeroRepositoryImpl(superHeroSource)
    }

}