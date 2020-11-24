package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class DisplayConnectorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_connectors)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.scanArray).apply {
            text = message
        }
    }
}
