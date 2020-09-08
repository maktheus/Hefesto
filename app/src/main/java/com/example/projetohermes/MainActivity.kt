package com.example.projetohermes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Person(val name: String, val age: Int)

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_SPEACH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        voice.setOnClickListener(){
            speak();
        }
    }

    fun proximaTela(view: View?) {
        val intent = Intent(this, chart::class.java)
        startActivity(intent)
    }




    private fun speak() {
        val myIntent=  Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        myIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        myIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        myIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something")
        try{
            startActivityForResult(myIntent, REQUEST_CODE_SPEACH_INPUT)
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEACH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    textView.text = result!![0]
                    val voice = result!![0]
                    LUIS_API(voice)
                }
            }
        }
    }




    private fun LUIS_API(voice: String){
        val url = "https://westus.api.cognitive.microsoft.com/luis/prediction/v3.0/apps/3fce88ca-6baf-47ef-8837-9bd31f17e28a/slots/production/predict?subscription-key=bfae06c098d44bcbb5b6ad961d67bf8b&verbose=true&show-all-intents=true&log=true&query=$voice"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                textView2.text = "Response: %s".format(response.toString())
                JSONparse()
            },
            { error ->
                textView2.text ="ppp"
            }
        )
        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }


    private fun JSONparse(){

    }
}

class Responsejson()