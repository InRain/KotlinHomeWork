package com.example.kotlinhomework;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        private const val NAME_KEY = "com.example.kotlinhomework.MainActivity.NAME_KEY"
    }

    private lateinit var greetings: String
    private lateinit var name: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greetings = getString(R.string.hello)
        name = savedInstanceState?.getString(NAME_KEY) ?: getString(R.string.anon)


        buttonNameYourSelf.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SecondActivity.GET_NAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondActivity.GET_NAME_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val nameFromData = data.extras?.getString(SecondActivity.NAME_KEY)

            nameFromData?.let {
                name = nameFromData
            }
        }
    }

    override fun onResume() {
        super.onResume()
        textViewHello?.text = getString(R.string.hello_message, greetings, name)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NAME_KEY, name)
    }
}