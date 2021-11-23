package com.khoirullatif.footballstandings.ui.standing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoirullatif.footballstandings.databinding.ActivityStandingBinding
import com.khoirullatif.footballstandings.model.SeasonItems

class StandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStandingBinding
    private lateinit var seasonItem: SeasonItems
    private lateinit var idLeague: String
    private lateinit var adapter: StandingAdapter
    private lateinit var standingViewModel: StandingViewModel

    companion object {
        const val SEASON_YEAR = "season_year"
        const val ID_LEAGUE = "id_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        standingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(StandingViewModel::class.java)

        seasonItem = intent.extras?.getParcelable(SEASON_YEAR)!!
        idLeague = intent.extras?.getString(ID_LEAGUE)!!

        binding.tvTitleLeague.text = seasonItem.displayName

        showLoading(true)
        adapter = StandingAdapter()
        adapter.notifyDataSetChanged()

        binding.rvStanding.layoutManager = LinearLayoutManager(this)
        binding.rvStanding.adapter = adapter

        standingViewModel.getStanding(idLeague, seasonItem.year.toString()).observe(this, {
            if (it != null) {
                adapter.setStanding(it)
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