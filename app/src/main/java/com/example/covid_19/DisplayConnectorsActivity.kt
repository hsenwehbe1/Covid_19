package com.example.covid_19

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DisplayConnectorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_connectors)

        val message = intent.getStringArrayExtra("strings")
        val APs: MutableList<MutableList<String>> = ArrayList()
        for(i in message.indices){
            val elementArray= message[i].split("#").toMutableList()
            APs.add(elementArray)
        }
        // know we have list of APs
        val List_of_numbers: MutableList<String> = ArrayList()
       for(i in APs.indices){
           // get the list of devices then the
           // list of numbers
           val numbers: MutableList<String> = ArrayList()
           numbers.add("70 354 224")
           numbers.add("71 354 441")
           numbers.add("81 633 291")
           //query the results
           while(data.getToNext()){
                List_of_numbers.add(data.getString(1))
           }
       }

    }
}
