package com.example.covid_19

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        fun scanSuccess() {
            val results = wifiManager.scanResults
            val intent = Intent(this,DisplayConnectorsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE,results.size.toString())
            }
            startActivity(intent)
//            Log.i("results",results.size.toString())

        }

        fun scanFailure() {
            // handle failure: new scan did NOT succeed
            // consider using old scan results: these are the OLD results!
            val results = wifiManager.scanResults
//        ... potentially use older scan results ...
        }
        val Btn_clicked = findViewById(R.id.get_access) as Button
        Btn_clicked.setOnClickListener {
            //first we need to get the list of devices from each access point
            val wifiScanReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                    if (success) {
                        scanSuccess()
                    } else {
                        scanFailure()
                    }
                }
            }

            val intentFilter = IntentFilter()
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            this.applicationContext.registerReceiver(wifiScanReceiver, intentFilter)
//            val results = wifiManager.scanResult
        }

    }
}
