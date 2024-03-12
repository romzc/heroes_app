package com.romzc.secondapp.infraestructure.repositories

import com.romzc.secondapp.domain.datasources.SuperHeroDatasource
import com.romzc.secondapp.domain.entities.SuperHero
import com.romzc.secondapp.domain.repositories.SuperHeroRepository
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class SuperHeroRepositoryImpl @Inject constructor(
    private val superHeroDataSource: SuperHeroDatasource
) : SuperHeroRepository() {

    override suspend fun getSuperheros(heroName: String): List<SuperHero>{
        return superHeroDataSource.getSuperheros(heroName)
    }

    override suspend fun getSuperHeroDetail(heroId: String): SuperHero {
        return superHeroDataSource.getSuperHeroDetail(heroId)
    }
}