package com.example.a37527


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.a37527.Adapter.WordAdapter
import com.example.a37527.data.WordData
import com.example.a37527.databinding.ActivityAddBinding
import com.example.a37527.model.WordModel
import com.google.gson.Gson

class AddActivity : AppCompatActivity() {

    lateinit var wordData: WordData
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        loadData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        var adapter = WordAdapter(wordData.ListWord)

        val wordRecyclerView = findViewById<RecyclerView>(R.id.word_recycler_view)
        wordRecyclerView.adapter = adapter;

        val swipeDel = object : SwipetoDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.absoluteAdapterPosition
                wordData.ListWord.removeAt(pos)
                saveData()
                wordRecyclerView.adapter?.notifyItemRemoved(pos)
            }
        }

        val itemHelper = ItemTouchHelper(swipeDel)
        itemHelper.attachToRecyclerView(wordRecyclerView)

        val add_btn = findViewById<Button>(R.id.add_w_btn)
        add_btn.setOnClickListener {
            this.createNewWord()
        }

        val wordSearch = findViewById<SearchView>(R.id.search_word)

//        val searchIcon =
        wordSearch.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
//            searchIcon.setColorFilter(Color.WHITE)

//        val cancelIcon =
        wordSearch.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
//            cancelIcon.setColorFilter(Color.WHITE)

//        val textView =
        wordSearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
//            textView.setTextColor(Color.WHITE)

        wordSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })
    }

//        val testBtn = findViewById<Button>(R.id.toTest)
//        testBtn.setOnClickListener() {
//            val intent = Intent(this, TestActivity::class.java)
//            startActivity(intent)
//        }
//        }
    fun createDefaultData(): WordData {
        var wordData = WordData(0, mutableListOf<WordModel>())
        wordData.addWord(WordModel(0, "rush", " /ɹʌʃ/", "vội vã"))
        wordData.addWord(WordModel(0, "tangled", "/táŋgəld/", "bị rối"))
        return wordData;
    }

    fun loadData() {
        var pref = getSharedPreferences("database", MODE_PRIVATE)
        var jsonData = pref.getString("wordData", null)
        if (jsonData != null) {
            this.wordData = Gson().fromJson<WordData>(jsonData, WordData::class.java)
        } else {
            this.wordData = createDefaultData()
        }
    }
    private fun saveData() {
        var pref = getSharedPreferences("database", MODE_PRIVATE)
        pref.edit().putString("wordData", wordData.toJsonString()).commit()

        Toast.makeText(this, "confirmed", Toast.LENGTH_SHORT).show()
    }

    private fun createNewWord() {
        val w = findViewById<EditText>(R.id.add_w).text.toString()
        val p = findViewById<EditText>(R.id.add_p).text.toString()
        val mean = findViewById<EditText>(R.id.add_m).text.toString()
        val newWord = WordModel(0, w, p, mean)
        this.wordData.addWord(newWord)
        saveData()

        var adapter = WordAdapter( wordData.ListWord)
        val wordRecyclerView = findViewById<RecyclerView>(R.id.word_recycler_view)
        wordRecyclerView.adapter = adapter;
    }

}