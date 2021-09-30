package com.example.projectclassandroidshahrivar.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectclassandroidshahrivar.MainActivity

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()