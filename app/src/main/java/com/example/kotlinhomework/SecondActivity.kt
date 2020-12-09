package com.example.kotlinhomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object {
        const val NAME_KEY = "com.example.kotlinhomework.SecondActivity.NAME_KEY"
        const val GET_NAME_REQUEST_CODE = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        buttonName.isEnabled = false

        buttonName.setOnClickListener {
            val intent = Intent()
            intent.putExtra(NAME_KEY, editTextTextPersonName.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                buttonName.isEnabled = !TextUtils.isEmpty(editable)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }
}