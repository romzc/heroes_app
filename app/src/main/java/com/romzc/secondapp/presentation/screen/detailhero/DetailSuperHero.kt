package com.romzc.secondapp.presentation.screen.detailhero

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.romzc.secondapp.R
import com.romzc.secondapp.databinding.FragmentDetailSuperHeroBinding
import com.romzc.secondapp.domain.entities.PowerStats
import com.romzc.secondapp.domain.entities.SuperHero
import com.romzc.secondapp.infraestructure.datasources.network.ApiService
import com.romzc.secondapp.infraestructure.models.PowerStatsResponse
import com.romzc.secondapp.infraestructure.models.SuperHeroDetailResponse
import com.romzc.secondapp.presentation.composables.HeroDetailScreen
import com.romzc.secondapp.presentation.viewmodel.SuperHeroViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class DetailSuperHero : Fragment() {

    private lateinit var binding: FragmentDetailSuperHeroBinding
    private val superHeroViewModel: SuperHeroViewModel by activityViewModels()
    private lateinit var compose: ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailSuperHeroBinding.inflate(inflater, container, false)
        superHeroViewModel.hero.observe(viewLifecycleOwner) {
            if (it != null) initUI(it)
        }
        return binding.root
    }

    private fun initUI(superHero: SuperHero) {
        compose = binding.composeView;
        compose.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    HeroDetailScreen(superHero = superHero)
                }
            }
        }
        Picasso.get().load(superHero.heroImage).into(binding.ivSuperHeroDetail)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailSuperHero().apply {
            arguments = Bundle()
        }
    }
}