package com.example.kotlinhomework;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }

    private var greetings: String? = null
    private var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greetings = getString(R.string.hello)
        name = if (savedInstanceState?.getString(NAME_KEY) != null) {
            savedInstanceState.getString(NAME_KEY)!!
        } else {
            getString(R.string.anon)
        }

        buttonNameYourSelf.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val nameFromData = data.extras?.getString(SecondActivity.NAME_KEY)
            if (nameFromData != null) {
                name = nameFromData
            }
        }
    }

    override fun onResume() {
        super.onResume()
        textViewHello?.text = "$greetings, $name"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }
}