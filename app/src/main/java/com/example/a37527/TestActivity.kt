package com.example.a37527

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.a37527.data.QuestionData
import com.example.a37527.data.WordData
import com.google.gson.Gson

class TestActivity : AppCompatActivity() {

    lateinit var quesData: QuestionData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun loadData() {
        var pref = getSharedPreferences("database", MODE_PRIVATE)
        var jsonData = pref.getString("wordData", null)
        if (jsonData != null) {
            this.quesData = Gson().fromJson<QuestionData>(jsonData, QuestionData::class.java)
        } else
            Toast.makeText(this, "not enough words", Toast.LENGTH_SHORT).show()
    }
}