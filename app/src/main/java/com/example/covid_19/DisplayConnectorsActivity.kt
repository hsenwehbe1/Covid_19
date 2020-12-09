package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.TextView

class DisplayConnectorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_connectors)

        val message = intent.getStringArrayExtra("strings")
        var APs = ""
        for(i in message.indices){
            val elementArray = message[i].split("#").toTypedArray()
            for(j in elementArray.indices){
                if(j==elementArray.size-1) APs= APs+elementArray[j]+"\n" else APs= APs+elementArray[j]+" "
            }
        }
        //know we have list of APs

        val textView = findViewById<TextView>(R.id.scanArray).apply {
            text = "/n"+APs
        }
    }
}
