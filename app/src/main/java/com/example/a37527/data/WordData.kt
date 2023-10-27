package com.example.a37527.data

import com.example.a37527.model.WordModel
import com.google.gson.Gson


data class WordData (var maxID: Int, var ListWord: MutableList<WordModel>) {
    public fun addWord(word:WordModel) {
        maxID += 1
        word.id = maxID
        ListWord.add(word)
    }

    public fun toJsonString() : String {
        var gson = Gson()
        return  gson.toJson(this)
    }
}