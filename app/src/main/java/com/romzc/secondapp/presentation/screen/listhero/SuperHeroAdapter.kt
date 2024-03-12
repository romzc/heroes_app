package com.romzc.secondapp.presentation.screen.listhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romzc.secondapp.R
import com.romzc.secondapp.domain.entities.SuperHero

class SuperHeroAdapter(
    private var heroes: List<SuperHero> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(list: List<SuperHero>) {
        this.heroes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_super_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(heroes[position], onItemSelected)

    }

    override fun getItemCount(): Int = heroes.size
}