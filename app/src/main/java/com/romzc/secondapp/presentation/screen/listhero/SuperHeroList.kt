package com.romzc.secondapp.presentation.screen.listhero

import android.content.Context
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
import com.romzc.secondapp.presentation.callbacks.OnSuperHeroClickListener
import com.romzc.secondapp.presentation.viewmodel.SuperHeroListViewModel
import com.romzc.secondapp.presentation.viewmodel.SuperHeroViewModel


class SuperHeroList : Fragment() {

    private lateinit var binding: FragmentSuperHeroListBinding
    private lateinit var heroAdapter: SuperHeroAdapter
    private val heroesViewModel: SuperHeroListViewModel by activityViewModels()
    private val selectedHeroViewModel: SuperHeroViewModel by activityViewModels()
    private var listener: OnSuperHeroClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        heroAdapter = SuperHeroAdapter { id ->
            selectedHeroViewModel.searchByIdHero(id)
            listener?.onSuperHeroClick(id)
        }
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(context)
        binding.rvSuperHero.adapter = heroAdapter
        heroesViewModel.heroes.observe(viewLifecycleOwner) {
            heroAdapter.updateList(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSuperHeroClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSuperHeroClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SuperHeroList().apply {
            arguments = Bundle()
        }
    }
}