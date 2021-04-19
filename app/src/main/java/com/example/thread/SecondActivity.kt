package com.example.thread

import android.app.Activity
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SecondActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val take=findViewById<Button>(R.id.button2)
        val add=findViewById<Button>(R.id.button3)
        val reset=findViewById<Button>(R.id.button4)
        val res = findViewById<TextView>(R.id.textView)
        val tbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        res.text= initial.toString()
        tbar.setNavigationOnClickListener {
            onBackPressed()
        }
        take.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val result=res.text.toString().toInt()-1
                if(result==0)
                {
                    delay(1000)
                    reset.visibility=View.VISIBLE
                    take.visibility= View.INVISIBLE
                    add.visibility= View.INVISIBLE
                }
                res.text=result.toString()
            }
        }
        add.setOnClickListener {
            val plus=GlobalScope.async(Dispatchers.Main) {
                val result=res.text.toString().toInt()+1
                if(result==maximum )
                {
                    delay(1000)
                    reset.visibility=View.VISIBLE
                    take.visibility= View.INVISIBLE
                    add.visibility= View.INVISIBLE
                }
                res.text=result.toString()
            }
            GlobalScope.launch(Dispatchers.Main)
            {
                plus.await()
            }
        }
        reset.setOnClickListener {
            reset.visibility = View.INVISIBLE
            take.visibility = View.VISIBLE
            add.visibility = View.VISIBLE
            res.text= initial.toString()
        }
    }
}