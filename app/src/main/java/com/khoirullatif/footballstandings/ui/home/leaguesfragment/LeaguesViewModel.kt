package com.khoirullatif.footballstandings.ui.home.leaguesfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khoirullatif.footballstandings.model.LeagueItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class LeaguesViewModel : ViewModel() {
    private val listLeagues = MutableLiveData<ArrayList<LeagueItems>>()

    private fun setLeagues() {
        val listItem = ArrayList<LeagueItems>()

        val url = "https://api-football-standings.azharimm.site/leagues"
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
                    Log.d("LeaguesViewModel", "responObject: $responseObject")
                    val listData = responseObject.getJSONArray("data")

                    for (i in 0 until listData.length()) {
                        val itemList = listData.getJSONObject(i)
                        val leagueItems = LeagueItems()
                        leagueItems.id = itemList.getString("id")
                        leagueItems.logo = itemList.getJSONObject("logos").getString("light")
                        leagueItems.name = itemList.getString("name")

                        listItem.add(leagueItems)
                    }
                    //post value ke mutablelivedata
                    listLeagues.postValue(listItem)
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
                Log.d( "Leagues onFailure:", error?.message.toString())
            }

        })
    }

    fun getLeagues(): LiveData<ArrayList<LeagueItems>> {
        setLeagues()
        return listLeagues
    }
}