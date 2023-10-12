package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Toast
import com.example.contactapp.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactDatabase:  contactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        contactDatabase = contactDatabase.getDatabase(this)
        binding.saveBtn.setOnClickListener {
            saveData()
        }


    }




    @OptIn(DelicateCoroutinesApi::class)
    private fun saveData() {
        val firstName = binding.nameET.text.toString()
        val lastName = binding.emailET.text.toString()
        val rollNo = binding.phoneET.text.toString()

        if(firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()){
            val contact = contactDatabase(null, Name,Email , Phone.toInt())
            GlobalScope.launch (Dispatchers.IO ){
                contactDatabase.contactDao().insert(contact)
            }
            binding.nameET.text.clear()
            binding.emailET.text.clear()
            binding.phoneET.text.clear()

            Toast.makeText(this@MainActivity,"Data saved", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@MainActivity,"Please enter all the data", Toast.LENGTH_SHORT).show()
        }

    }