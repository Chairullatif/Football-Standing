package com.khoirullatif.footballstandings.ui.season

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoirullatif.footballstandings.R
import com.khoirullatif.footballstandings.databinding.ItemSeasonBinding
import com.khoirullatif.footballstandings.model.SeasonItems
import com.khoirullatif.footballstandings.ui.standing.StandingActivity

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    private val listSeasonItems = ArrayList<SeasonItems>()
    private var mContext: Context? = null
    private lateinit var logo: String
    private lateinit var idLeague: String

    fun setSeasons(list: ArrayList<SeasonItems>, context: Context, logo: String, id: String) {
        mContext = context
        listSeasonItems.clear()
        listSeasonItems.addAll(list)
        notifyDataSetChanged()
        this.logo = logo
        idLeague = id
    }

    inner class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSeasonBinding.bind(itemView)
        fun bind(data: SeasonItems) {
            binding.tvSeason.text = data.displayName
            binding.tvYear.text = data.year
            Glide.with(mContext!!)
                .load(logo)
                .into(binding.imgLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_season, parent, false)
        return SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val data = listSeasonItems[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, StandingActivity::class.java)
            intent.putExtra(StandingActivity.SEASON_YEAR, data)
            intent.putExtra(StandingActivity.ID_LEAGUE, idLeague)
            Log.d("SeasonAdapter", "dataId: ${data.year}")
            mContext?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listSeasonItems.size
    }
}