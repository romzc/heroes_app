package com.romzc.secondapp.presentation.screen.detailhero

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romzc.secondapp.R
import com.romzc.secondapp.databinding.FragmentDetailSuperHeroBinding
import com.romzc.secondapp.infraestructure.datasources.network.ApiService
import com.romzc.secondapp.infraestructure.models.PowerStatsResponse
import com.romzc.secondapp.infraestructure.models.SuperHeroDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class DetailSuperHero : Fragment() {

    private lateinit var binding: FragmentDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailSuperHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initUI(superHero: SuperHeroDetailResponse) {
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


    companion object {
        @JvmStatic
        fun newInstance() = DetailSuperHero().apply {
            arguments = Bundle()
        }
    }
}