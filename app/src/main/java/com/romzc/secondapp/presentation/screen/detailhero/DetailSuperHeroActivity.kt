package com.romzc.secondapp.presentation.screen.detailhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.romzc.secondapp.databinding.ActivityDeatilSuperHeroBinding
import com.romzc.secondapp.infraestructure.datasources.network.ApiService
import com.romzc.secondapp.infraestructure.models.PowerStatsResponse
import com.romzc.secondapp.infraestructure.models.SuperHeroDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeatilSuperHeroBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeatilSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)

            if (superHeroDetail.body() != null) {
                runOnUiThread {
                    //createUI(superHeroDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.imageUrl.url).into(binding.ivSuperHeroDetail)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerStats)
        binding.tvSuperHeroRealName.text = superHero.biography.fullName
        binding.tvSuperHeroPublisher.text = superHero.biography.publisher
    }

    private fun prepareStats(powerStatsResponse: PowerStatsResponse) {

        updateHeight(binding.viewCombat, powerStatsResponse.combat)
        updateHeight(binding.viewDurability, powerStatsResponse.durability)
        updateHeight(binding.viewIntelligence, powerStatsResponse.intelligence)
        updateHeight(binding.viewPower, powerStatsResponse.power)
        updateHeight(binding.viewSpeed, powerStatsResponse.speed)
        updateHeight(binding.viewStrength, powerStatsResponse.strength)

    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}