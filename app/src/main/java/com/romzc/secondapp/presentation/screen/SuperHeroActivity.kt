package com.romzc.secondapp.presentation.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.romzc.secondapp.databinding.ActivitySuperHeroeListBinding
import com.romzc.secondapp.presentation.screen.detailhero.DetailSuperHeroActivity
import com.romzc.secondapp.presentation.screen.listhero.SuperHeroList
import com.romzc.secondapp.presentation.viewmodel.SuperHeroListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroeListBinding
    private val heroesViewModel: SuperHeroListViewModel by viewModels()

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

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(DetailSuperHeroActivity.EXTRA_ID, id)
        startActivity(intent)
    }
}