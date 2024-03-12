package com.romzc.secondapp.infraestructure.models

import com.romzc.secondapp.domain.entities.PowerStats
import com.romzc.secondapp.domain.entities.SuperHero

class NetworkSuperHero(
    private val id: String,
    private val name: String,
    private val image: String,
    private val stats: PowerStats
) {

    companion object {
        fun fromJson(superHero: SuperHeroItemResponse): NetworkSuperHero {
            return NetworkSuperHero(
                id = superHero.superHeroId,
                name = superHero.superHeroName,
                image = superHero.superHeroImage.url,
                stats = PowerStats(
                    intelligence = superHero.superHeroStats.power.toIntOrNull() ?: 0,
                    power = superHero.superHeroStats.power.toIntOrNull() ?: 0,
                    combat = superHero.superHeroStats.combat.toIntOrNull() ?: 0,
                    durability = superHero.superHeroStats.durability.toIntOrNull() ?: 0,
                    speed = superHero.superHeroStats.speed.toIntOrNull() ?: 0,
                    strength = superHero.superHeroStats.strength.toIntOrNull() ?: 0,
                )
            )
        }
    }

    fun toSuperHero() = SuperHero(
        heroId = id,
        heroName = name,
        heroImage = image,
        powerStats = stats.copy()
    )

}