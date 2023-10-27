package com.example.a37527.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.example.a37527.MainActivity
import com.example.a37527.R

import com.example.a37527.model.WordModel
import java.util.Locale


class WordAdapter( val dataset: MutableList<WordModel> )
    : RecyclerView.Adapter<WordAdapter.ItemViewHolder>(), Filterable{
    var wordFilterList = ArrayList<WordModel>()
    init {
        wordFilterList = dataset as ArrayList<WordModel>
    }
    class ItemViewHolder(private  val view: View) : RecyclerView.ViewHolder(view) {
        val eWord:TextView = view.findViewById(R.id.New_word)
        val phonetic:TextView = view.findViewById(R.id.phonetic)
        val meaning:TextView = view.findViewById(R.id.meaning)
//        val xBtn:ImageButton = view.findViewById(R.id.revBtn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.word_item_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return wordFilterList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = wordFilterList[position]
        holder.eWord.text = item.E_word
        holder.phonetic.text = item.phonetic
        holder.meaning.text = item.meaning
//        holder.xBtn.setOnClickListener(deleteItem(position))
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty()) {
                    wordFilterList = dataset as ArrayList<WordModel>
                } else {
                    val resultList = ArrayList<WordModel>()
                    for(row in dataset ) {
                        if(row.E_word.lowercase(Locale.ROOT).contains(charSearch.lowercase()))
                        {
                            resultList.add(row)
                        }
                    }
                    wordFilterList = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = wordFilterList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                wordFilterList = results?.values as ArrayList<WordModel>
                notifyDataSetChanged()
            }

        }
    }


}