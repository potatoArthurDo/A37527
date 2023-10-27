package com.example.a37527.data

import com.example.a37527.model.QuestionModel
import com.google.gson.Gson

data class QuestionData (var maxID: Int, var ListQues: ArrayList<QuestionModel>) {
    public fun addWord(word: QuestionModel) {
        maxID += 1
        word.id = maxID
        word.que = ListQues[0].toString()
        word.ans = ListQues[2].toString()
        word.op1 = word.ans
        word.op2 = "not"
        word.op3 = "not"
        word.op4 = "not"
        ListQues.add(word)
    }

    public fun toJsonString() : String {
        var gson = Gson()
        return  gson.toJson(this)
    }
}