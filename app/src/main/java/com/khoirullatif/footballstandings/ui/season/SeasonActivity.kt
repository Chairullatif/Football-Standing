package com.khoirullatif.footballstandings.ui.season

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.khoirullatif.footballstandings.databinding.ActivitySeasonBinding
import com.khoirullatif.footballstandings.model.LeagueItems

class SeasonActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeasonBinding
    private lateinit var league: LeagueItems
    private lateinit var adapter: SeasonAdapter
    private lateinit var seasonViewModel: SeasonViewModel

    companion object {
        const val LEAGUE_ID = "league_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        league = intent.extras?.getParcelable(LEAGUE_ID)!!
        Glide.with(this)
            .load(league.logo)
            .into(binding.ivLogo)
        binding.tvTitleLeague.text = league.name
        seasonViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SeasonViewModel::class.java)

        adapter = SeasonAdapter()
        adapter.notifyDataSetChanged()

        showLoading(true)
        binding.rvSeason.layoutManager = LinearLayoutManager(this)
        binding.rvSeason.adapter = adapter

        seasonViewModel.getLeagues(league.id.toString()).observe(this, {
            if (it != null) {
                adapter.setSeasons(it, this, league.logo.toString(), league.id.toString())
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}