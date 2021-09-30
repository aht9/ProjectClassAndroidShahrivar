package com.example.projectclassandroidshahrivar.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectclassandroidshahrivar.R
import com.example.projectclassandroidshahrivar.api.OmDbService
import com.example.projectclassandroidshahrivar.detail.DetailFragment
import com.example.projectclassandroidshahrivar.model.MovieSearch
import com.example.projectclassandroidshahrivar.model.Search
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class CustomDialogClass(context: Context,
                        title:String,
                        private val cellClickListener: CellClickListenerOnCustomDialog) : Dialog(context),CellClickListener {

    private val titleForSearch : String
    private val TAG = "CustomDialogClass"

    init {
        setCancelable(true)
        titleForSearch = title
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_custom_searchresult)

        val retrofit = Retrofit.Builder()
            .baseUrl(OmDbService.API_URL)
            .client(getHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(OmDbService::class.java)
        service.searchByTitle(titleForSearch,"137d5bf1").enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                showRecycler(response)
            }
            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })

    }

    fun showRecycler(response: Response<MovieSearch>) : Unit{
        val rcSearch = findViewById<RecyclerView>(R.id.rc_searchBox)
        rcSearch.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        val data = response.body()!!.Search
        val adapter = SearchMovieAdapter(data,this)
        rcSearch.adapter = adapter

    }

    private fun getHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor { chain ->
            val requestWithUserAgent = chain.request().newBuilder()
                .header("User-Agent", "My custom user agent")
                .build()
            chain.proceed(requestWithUserAgent)
        }
        return okHttpBuilder.build()
    }


    override fun onCellClickListener(data: Search) {

        cellClickListener.onCellClickListenerOnCustomDialog(data)
        this.dismiss()
//        val fm = fragmentManager
//        fm!!.beginTransaction()
//            .replace(R.id.fragmentContainer,DetailFragment(),"DetailFragment")
//            .addToBackStack(null).commit()


    }

}

interface CellClickListener {
    fun onCellClickListener(data: Search)
}