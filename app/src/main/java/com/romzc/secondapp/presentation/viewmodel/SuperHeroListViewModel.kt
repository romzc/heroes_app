package com.romzc.secondapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romzc.secondapp.domain.entities.SuperHero
import com.romzc.secondapp.domain.repositories.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SuperHeroListViewModel @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {
    private val _heroes = MutableLiveData<List<SuperHero>>(emptyList())
    val heroes: LiveData<List<SuperHero>> = _heroes

    init {
        loadHeroes();

    }

    private fun loadHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _heroes.postValue(repository.getSuperheros(""))
        }
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _heroes.postValue(repository.getSuperheros(query))
        }
    }
}