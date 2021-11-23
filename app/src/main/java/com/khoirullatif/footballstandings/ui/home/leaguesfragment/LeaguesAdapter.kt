package com.khoirullatif.footballstandings.ui.home.leaguesfragment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoirullatif.footballstandings.R
import com.khoirullatif.footballstandings.databinding.ItemLeaguesBinding
import com.khoirullatif.footballstandings.model.LeagueItems
import com.khoirullatif.footballstandings.ui.season.SeasonActivity

class LeaguesAdapter : RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder>() {

    private val listLeagueItems = ArrayList<LeagueItems>()
    private var mContext: Context? = null

    fun setListLeague(list: ArrayList<LeagueItems>, context: Context) {
        mContext = context
        listLeagueItems.clear()
        listLeagueItems.addAll(list)
        notifyDataSetChanged()
    }

    inner class LeaguesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLeaguesBinding.bind(itemView)
        fun bind(data: LeagueItems) {
            binding.tvLeague.text = data.name
            Glide.with(itemView.context)
                .load(data.logo)
                .into(binding.imgLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leagues, parent, false)
        return LeaguesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
        val data = listLeagueItems[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, SeasonActivity::class.java)
            intent.putExtra(SeasonActivity.LEAGUE_ID, data)
            Log.d("LeagueAdapter", "dataId: ${data.id}")
            mContext?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listLeagueItems.size
    }
}