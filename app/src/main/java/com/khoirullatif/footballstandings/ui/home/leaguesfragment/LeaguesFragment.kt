package com.khoirullatif.footballstandings.ui.home.leaguesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.khoirullatif.footballstandings.databinding.FragmentLeaguesBinding

class LeaguesFragment : Fragment() {

    private lateinit var binding: FragmentLeaguesBinding
    private lateinit var adapter: LeaguesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLeaguesBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leaguesViewModel : LeaguesViewModel by activityViewModels()

        adapter = LeaguesAdapter()
        adapter.notifyDataSetChanged()

        showLoading(true)
        binding.rvLeague.layoutManager = GridLayoutManager(view.context, 2)
        binding.rvLeague.adapter = adapter

        leaguesViewModel.getLeagues().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setListLeague(it, view.context)
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