package com.romzc.secondapp.domain.repositories

import com.romzc.secondapp.domain.entities.SuperHero

abstract class SuperHeroRepository {
    abstract suspend fun getSuperheros(heroName: String): List<SuperHero>;
    abstract suspend fun getSuperHeroDetail(heroId: String): SuperHero;
}