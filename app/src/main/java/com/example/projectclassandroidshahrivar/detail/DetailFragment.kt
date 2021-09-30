package com.example.projectclassandroidshahrivar.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectclassandroidshahrivar.R

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments

        if (bundle != null) {
            // handle your code here.
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val bundle = this.arguments
        var inputText :String
        if (bundle != null) {
            inputText = bundle.getString("id").toString()
        }

        return view
    }

}