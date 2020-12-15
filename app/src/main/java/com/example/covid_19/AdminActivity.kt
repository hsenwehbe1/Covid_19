package com.example.covid_19

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.lang.Integer.parseInt


class AdminActivity : AppCompatActivity() {
    val Database = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val create_Btn = findViewById<Button>(R.id.create_column)
        create_Btn.setOnClickListener {
            try{
                val name =  findViewById<EditText>(R.id.create_name)
                val number = findViewById<EditText>(R.id.create_number)
                val data = Database.addData(name.text.toString(),parseInt(number.text.toString()))
                Log.i("return",data.toString())
                if(data) toastMessage("successfully created") else toastMessage("unexpected error")
            }catch(e: Exception) {
                e.message?.let { it1 -> Log.i("error","no") }
            }

        }
    }
    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
