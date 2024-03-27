package com.romzc.secondapp.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.romzc.secondapp.databinding.ActivitySuperHeroeListBinding
import com.romzc.secondapp.presentation.callbacks.OnSuperHeroClickListener
import com.romzc.secondapp.presentation.screen.detailhero.DetailSuperHero
import com.romzc.secondapp.presentation.screen.listhero.SuperHeroList
import com.romzc.secondapp.presentation.viewmodel.SuperHeroListViewModel
import com.romzc.secondapp.presentation.viewmodel.SuperHeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroActivity: AppCompatActivity(), OnSuperHeroClickListener  {

    private lateinit var binding: ActivitySuperHeroeListBinding
    private val heroesViewModel: SuperHeroListViewModel by viewModels()
    private val selectedHeroViewModel: SuperHeroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroeListBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI() {
        changeFragment(SuperHeroList.newInstance())
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fcvSuperHero.id, fragment)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSuperHeroClick(id: String) {
        changeFragment(DetailSuperHero.newInstance())
    }
}