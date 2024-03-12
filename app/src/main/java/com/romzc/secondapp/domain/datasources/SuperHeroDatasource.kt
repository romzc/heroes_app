package com.romzc.secondapp.domain.datasources

import com.romzc.secondapp.domain.entities.SuperHero

abstract class SuperHeroDatasource {
    abstract suspend fun getSuperheros(heroName: String): List<SuperHero>;
    abstract suspend fun getSuperHeroDetail(heroId: String): SuperHero;
}