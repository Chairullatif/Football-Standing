package com.khoirullatif.footballstandings.ui.standing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoirullatif.footballstandings.R
import com.khoirullatif.footballstandings.databinding.ItemStandingBinding
import com.khoirullatif.footballstandings.model.StandingItems

class StandingAdapter : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    private val listStandingItems = ArrayList<StandingItems>()

    fun setStanding(list: ArrayList<StandingItems>) {
        listStandingItems.clear()
        listStandingItems.addAll(list)
        notifyDataSetChanged()
    }

    inner class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemStandingBinding.bind(itemView)
        fun bind(data: StandingItems) {
            binding.tvNumber.text = data.rank
            binding.tvTeam.text = data.nameTeam
            Glide.with(itemView.context)
                .load(data.logo)
                .into(binding.imgLogo)

            binding.tvPlayed.text = data.played
            binding.tvDiff.text = data.diff
            binding.tvPoint.text = data.points
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        return StandingViewHolder(view)
    }

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        val data = listStandingItems[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listStandingItems.size
    }
}