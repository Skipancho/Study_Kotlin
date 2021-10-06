package com.e.kotlin_test1

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener : View.OnClickListener = View.OnClickListener {
            when{
                it.id == R.id.btn_1 -> result_tv.text = "button 1 action"
                it.id == R.id.btn_2 -> startActivity(Intent(this,DaeguFoodActivity::class.java))
            }
        }

        tv_1.text = "Hello Kotlin"
        btn_1.setOnClickListener(listener)
        btn_2.setOnClickListener(listener)
    }
}