package com.example.hellokittyquiz
import Question
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import java.lang.reflect.Array.get

private val TAG = "QuizViewModel"
class QuizViewModel:ViewModel() {
    // create index counter3
    private var currentIndex = 0
    private val TAG = "MainActivity"

    // load my questions up by creating a list of Question objects
    private val QuestionBank = listOf(
        Question(R.string.kitty1, true),
        Question(R.string.kitty2, false),
        Question(R.string.kitty3, false),
        Question(R.string.kitty4, true)
    )
    val currentQuestionAnswer:Boolean
    get() = QuestionBank[currentIndex].answer

    val currentQuestionText: Int
    get() = QuestionBank[currentIndex].textReId

    fun moveToNext(){
        currentIndex = (currentIndex+1)%QuestionBank.size
    }
    fun moveToPrevious(){
        currentIndex = (QuestionBank.size + (currentIndex+1))%QuestionBank.size
    }
    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "ViewModel instance is cleared")
    }
}