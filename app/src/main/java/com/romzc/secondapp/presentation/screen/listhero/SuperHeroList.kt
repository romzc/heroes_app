package com.romzc.secondapp.presentation.screen.listhero

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.romzc.secondapp.databinding.FragmentSuperHeroListBinding
import com.romzc.secondapp.presentation.screen.detailhero.DetailSuperHeroActivity
import com.romzc.secondapp.presentation.viewmodel.SuperHeroListViewModel


class SuperHeroList : Fragment() {

    private lateinit var binding: FragmentSuperHeroListBinding
    private lateinit var heroAdapter: SuperHeroAdapter
    private val heroesViewModel: SuperHeroListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSuperHeroListBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.searchViewHero.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.progressBar.isVisible = true
                heroesViewModel.searchHeroes(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        heroAdapter = SuperHeroAdapter{id -> navigateToDetail(id)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(context)
        binding.rvSuperHero.adapter = heroAdapter
        heroesViewModel.heroes.observe(viewLifecycleOwner) {
            heroAdapter.updateList(it)
        }
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(context, DetailSuperHeroActivity::class.java)
        intent.putExtra(DetailSuperHeroActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SuperHeroList().apply {
            arguments = Bundle()
        }
    }
}