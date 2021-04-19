package com.example.thread

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged

var initial:Int=0;
var maximum:Int=0;
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inq=findViewById<EditText>(R.id.editTextNumberSigned)
        val maxq=findViewById<EditText>(R.id.editTextNumberSigned2)
        val smb=findViewById<Button>(R.id.button)
        inq.doAfterTextChanged {
            if(inq.text.isEmpty())
                smb.isEnabled=false
            else if(maxq.text.isNotEmpty())
            {
                if(inq.text.toString().toInt()>=maxq.text.toString().toInt())
                {
                    inq.error="Initial must be less than maximum"
                    smb.isEnabled=false
                }
                else {
                    maxq.error=null
                    smb.isEnabled=true
                }
            }
        }
        maxq.doAfterTextChanged {
            if(maxq.text.isEmpty())
                smb.isEnabled=false
            else if(inq.text.isNotEmpty())
            {
                if(inq.text.toString().toInt()>=maxq.text.toString().toInt() || maxq.text.toString().toInt()<0)
                {
                    maxq.error="Maximum must be greater than initial"
                    smb.isEnabled=false
                }
                else {
                    inq.error=null
                    smb.isEnabled=true
                }
            }
        }
        smb.setOnClickListener {
            initial=inq.text.toString().toInt()
            maximum=maxq.text.toString().toInt()
            val intent= Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }
}