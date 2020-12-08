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
        var result=""
        for(i in message.indices){
            val elementArray = message[i].split("#").toTypedArray()
            for(j in elementArray.indices){
                if(j==elementArray.size-1) result= result+elementArray[j]+"\n" else result= result+elementArray[j]+" "
            }
        }

        val textView = findViewById<TextView>(R.id.scanArray).apply {
            text = "/n"+result
        }
    }
}
