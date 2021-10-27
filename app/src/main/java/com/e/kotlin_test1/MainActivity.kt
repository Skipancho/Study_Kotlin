package com.e.kotlin_test1

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private val coroutineScope : CoroutineScope = CoroutineScope(Dispatchers.Main);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result_tv.movementMethod = ScrollingMovementMethod()

        val listener : View.OnClickListener = View.OnClickListener {
            when{
                it.id == R.id.btn_1 -> result_tv.text = "button 1 action"
                it.id == R.id.btn_2 -> startActivity(Intent(this,DaeguFoodActivity::class.java))
                it.id == R.id.btn_3 -> writeText()
            }
        }

        tv_1.text = "Hello Kotlin"

        btn_1.setOnClickListener(listener)
        btn_2.setOnClickListener(listener)
        btn_3.setOnClickListener(listener)


    }

    private fun startTask(view: View){
        coroutineScope.launch {

        }
    }

    private fun writeText(){
        var text = et_1.text.toString()
        result_tv.append(text+"\n")
    }
}