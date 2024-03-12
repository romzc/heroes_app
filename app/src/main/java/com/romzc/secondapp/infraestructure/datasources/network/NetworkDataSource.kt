package com.romzc.secondapp.infraestructure.datasources.network

import android.util.Log
import com.romzc.secondapp.domain.datasources.SuperHeroDatasource
import com.romzc.secondapp.domain.entities.SuperHero
import com.romzc.secondapp.infraestructure.models.NetworkSuperHero
import com.romzc.secondapp.infraestructure.models.SuperHeroItemResponse
import com.romzc.secondapp.infraestructure.models.SuperHeroResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import javax.inject.Inject


class NetworkDataSource @Inject constructor(
    private val retrofit: ApiService
) : SuperHeroDatasource() {

    override suspend fun getSuperheros(heroName: String): List<SuperHero> {
        val response: Response<SuperHeroResponse> = retrofit.getSuperheros(heroName)
        val heroes: SuperHeroResponse? = response.body()

        if (heroes == null || heroes.status.equals("error")) {
            Log.d("NETWORK_CALL", heroes.toString())
            return emptyList()
        }
        val responseHeroes = heroes.superHeroes.map { heroItemResponse ->
            NetworkSuperHero.fromJson(heroItemResponse).toSuperHero()
        }
        return responseHeroes
    }

    override suspend fun getSuperHeroDetail(heroId: String): SuperHero {
        val response: Response<SuperHeroItemResponse> = retrofit.getSuperHeroDetail(heroId)
        val hero: SuperHeroItemResponse? = response.body()

        if (hero != null) {
            return NetworkSuperHero.fromJson(hero).toSuperHero();
        }
        throw Exception()
    }
}