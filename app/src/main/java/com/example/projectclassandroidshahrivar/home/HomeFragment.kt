package com.example.projectclassandroidshahrivar.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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

class HomeFragment : Fragment() ,CellClickListenerOnCustomDialog{

    private val TAG = "HomeFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val retrofit = Retrofit.Builder()
            .baseUrl(OmDbService.API_URL)
            .client(getHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        val service = retrofit.create(OmDbService::class.java)
        service.searchByTitle("2021","137d5bf1").enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                showRecycler(view,response)
            }
            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })


        //SearchBox
        val search:EditText = view.findViewById(R.id.txt_search)
        search.setOnEditorActionListener { v, actionId, event ->
            if(actionId == 3){
                var searchTitle:String = search.text.toString()
                CustomDialogClass(requireContext(),searchTitle,this).show()
                true
            } else {
                false
            }
        }

        return view
    }

    fun showRecycler(view: View, response: Response<MovieSearch>) : Unit{
        val recyclerview = view.findViewById<RecyclerView>(R.id.rc_feature)
        recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        val data = response.body()!!.Search
        val adapter = FeatureMovieAdapter(data)
        recyclerview.adapter = adapter
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

    override fun onCellClickListenerOnCustomDialog(data: Search) {
        val bundle = Bundle()
        bundle.putString("id", data.imdbID)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        val fm = fragmentManager
        fm!!.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment,"DetailFragment")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit()
    }


}

interface CellClickListenerOnCustomDialog {
    fun onCellClickListenerOnCustomDialog(data: Search)
}

