package com.khoirullatif.footballstandings.ui.season

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khoirullatif.footballstandings.model.SeasonItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class SeasonViewModel : ViewModel() {
    private val listSeasons = MutableLiveData<ArrayList<SeasonItems>>()

    private fun setSeasons(id: String) {
        val listItem = ArrayList<SeasonItems>()
        Log.d("SeasonViewModel", "id: $id")
        val url = "https://api-football-standings.azharimm.site/leagues/${id}/seasons"

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
                    Log.d("SeasonViewModel", "responObject: $responseObject")
                    val responseData = responseObject.getJSONObject("data")
                    val listData = responseData.getJSONArray("seasons")

                    for (i in 0 until listData.length()) {
                        val itemList = listData.getJSONObject(i)
                        val seasonItems = SeasonItems()
                        seasonItems.year = itemList.getInt("year").toString()
                        seasonItems.displayName = itemList.getString("displayName")

                        listItem.add(seasonItems)
                    }
                    //post value ke mutablelivedata
                    listSeasons.postValue(listItem)
                    Log.d("SeasonViewModel", "year: ${listItem[1].displayName}")
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
                Log.d( "Season onFailure:", error?.message.toString())
            }

        })
    }

    fun getLeagues(id: String): LiveData<ArrayList<SeasonItems>> {
        setSeasons(id)
        return listSeasons
    }
}