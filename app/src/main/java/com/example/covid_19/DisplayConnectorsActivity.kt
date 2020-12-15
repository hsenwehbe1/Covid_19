package com.example.covid_19

import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity

class DisplayConnectorsActivity : AppCompatActivity() {
//    private val mListView: ListView? = null
    val Database= DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_connectors)
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
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
           //the below numbers are dummy data

           val numbers: MutableList<Int> = ArrayList()
           numbers.add(70354224)
           numbers.add(71354441)
           numbers.add(81633291)
           var Result_string = ""
           for(j in numbers.indices){
               val data = Database.getData(numbers[j])
               while(data.moveToNext()){
                   List_of_numbers.add(data.getString(0))
                   Result_string = Result_string+"name of infected person: "+data.getString(1)+"\n"+"Phone number: "+data.getString(0)+"\n \n \n \n"
               }
           }
           Log.i("List",List_of_numbers[0].toString())
           //create the list adapter and set the adapter
           val display_results = findViewById<TextView>(R.id.Resluts)
           display_results.text = Result_string

           // access the listView from xml file
           var mListView = findViewById<ListView>(R.id.userlist)

//           val adapter: ListAdapter =
//               ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List_of_numbers)
//           mListView?.setAdapter(adapter)
//
//           //set an onItemClickListener to the ListView
//
//           //set an onItemClickListener to the ListView
//           mListView?.setOnItemClickListener(OnItemClickListener { adapterView, view, i, l ->
//               val number = adapterView.getItemAtPosition(i).toString()
//           })

       }

    }
}
