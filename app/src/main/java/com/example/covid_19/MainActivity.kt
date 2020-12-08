package com.example.covid_19

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    val LOCATION_PERMISSION_CODE = 101
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== LOCATION_PERMISSION_CODE){
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(this,
                    "location Permission Granted",
                    Toast.LENGTH_SHORT)
                    .show();
            }
            else {
                Toast.makeText(this,
                    "Camera Permission Denied",
                    Toast.LENGTH_SHORT)
                    .show();
            }
            return
        }else{
            return
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        fun scanSuccess() {
            val results = wifiManager.scanResults
            //Log.i("name",results[0].SSID)
            val ap_values:MutableList<String> = ArrayList()
            val ap_valuess= arrayOf(String)
            for(result in results){
                val SSID = result.SSID
                val BSSID = result.BSSID
                var venueName = " "
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    venueName = result.venueName.toString()
                }
                val all =SSID+"#"+BSSID+"#"+venueName
                ap_values.add(all)
            }
            val intent = Intent(this,DisplayConnectorsActivity::class.java).apply {
                putExtra("strings",ap_values.toTypedArray())
            }
//            Log.i("support wifi: ",wifiManager.toString())
            startActivity(intent)
//            Log.i("results",results.size.toString())

        }

        fun scanFailure() {
            // handle failure: new scan did NOT succeed
            // consider using old scan results: these are the OLD results!
            val results = wifiManager.scanResults
//        ... potentially use older scan results ...
        }
        val Btn_clicked = findViewById<Button>(R.id.get_access)
        val Admin_Btn_clicked = findViewById<Button>(R.id.get_admin)
        Admin_Btn_clicked.setOnClickListener {

        }
        Btn_clicked.setOnClickListener {
            //first we need to get the list of devices from each access point


            val intentFilter = IntentFilter()
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                val permissions = Array<String>(1){Manifest.permission.ACCESS_COARSE_LOCATION}
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),LOCATION_PERMISSION_CODE)
                //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method

            }else{
                val wifiScanReceiver = object : BroadcastReceiver() {
                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun onReceive(context: Context, intent: Intent) {
                        val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                        if (success) {
                            scanSuccess()
                        } else {
                            scanFailure()
                        }
                    }
                }
                this.applicationContext.registerReceiver(wifiScanReceiver, intentFilter)
            }

        }

    }
}
