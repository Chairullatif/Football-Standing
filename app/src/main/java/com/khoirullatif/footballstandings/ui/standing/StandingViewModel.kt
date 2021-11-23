package com.khoirullatif.footballstandings.ui.standing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khoirullatif.footballstandings.model.StandingItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class StandingViewModel : ViewModel() {
    private val listStanding = MutableLiveData<ArrayList<StandingItems>>()

    private fun setStanding(id: String, year: String) {
        val listItem = ArrayList<StandingItems>()
        Log.d("SeasonViewModel", "id: $id")
        val url = "https://api-football-standings.azharimm.site/leagues/${id}/standings?season=${year}&sort=asc"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                //parsing data
                val result = String(responseBody!!)
                try {
                    val responseObject = JSONObject(result)
                    val responseData = responseObject.getJSONObject("data")
                    val listData = responseData.getJSONArray("standings")

                    for (i in 0 until listData.length()) {
                        val itemList = listData.getJSONObject(i)
                        val standingItems = StandingItems()
                        standingItems.rank = itemList.getJSONArray("stats")
                            .getJSONObject(8)
                            .getInt("value").toString()

                        if (itemList.getJSONObject("team").isNull("logos")) {
                            standingItems.logo = "https://static.thenounproject.com/png/3237447-200.png"
                        } else {
                            standingItems.logo = itemList.getJSONObject("team")
                                .getJSONArray("logos")
                                .getJSONObject(0).getString("href")
                        }

                        standingItems.nameTeam = itemList.getJSONObject("team")
                            .getString("name")
                        standingItems.played = itemList.getJSONArray("stats")
                            .getJSONObject(3)
                            .getInt("value").toString()
                        standingItems.diff = itemList.getJSONArray("stats")
                            .getJSONObject(9)
                            .getInt("value").toString()
                        standingItems.points = itemList.getJSONArray("stats")
                            .getJSONObject(6)
                            .getInt("value").toString()

                        listItem.add(standingItems)
                    Log.d("CekStandingViewModel", "logo: ${listItem[0].nameTeam}")
                    }
                    //post value ke mutablelivedata
                    listStanding.postValue(listItem)
                } catch (e: Exception) {
                    Log.d( "exception ", e.message.toString())
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d( "Standing onFailure:", error?.message.toString())
            }

        })
    }

    fun getStanding(id: String, year: String): LiveData<ArrayList<StandingItems>> {
        setStanding(id, year)
        return listStanding
    }
}