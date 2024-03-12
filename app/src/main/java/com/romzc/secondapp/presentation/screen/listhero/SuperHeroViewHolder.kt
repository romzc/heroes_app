package com.romzc.secondapp.presentation.screen.listhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.romzc.secondapp.databinding.ItemSuperHeroBinding
import com.romzc.secondapp.domain.entities.SuperHero
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperHeroBinding.bind(view)

    fun bind(
        superHero: SuperHero,
        onItemSelected: (String) -> Unit
    ) {
        binding.tvSuperHeroName.text = superHero.heroName
        Picasso.get().load(superHero.heroImage).into(binding.ivSuperHero)
        binding.root.setOnClickListener { onItemSelected(superHero.heroId) }
    }
}