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
class SuperHeroViewModel @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {

    private val _hero = MutableLiveData<SuperHero>(null)
    val hero: LiveData<SuperHero> = _hero

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    fun searchByIdHero(id: String) {
        _loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _hero.postValue(repository.getSuperHeroDetail(id))
            } catch (e: Exception) {
                // Manejo errores según sea necesario
            }
            finally {
                _loadingState.postValue(false)
            }
        }
    }
}