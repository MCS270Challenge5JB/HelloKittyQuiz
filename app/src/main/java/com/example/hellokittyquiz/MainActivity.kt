package com.example.hellokittyquiz

import Question
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    // error 1: declared in the wrong place
    private lateinit var trueButton: Button
    //error 2: declared in the wrong place
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private val quizViewModel:QuizViewModel by lazy{
        ViewModelProviders.of(this).get(QuizViewModel::class.java)

    }
    private val TAG = "MainActivity"
    private var count = 0
    private var correct = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,  "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val QuizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG,"We got a QuizViewModel!")

        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)


        fun checkAnswer(userAnswer: Boolean){
            val correctAnswer = quizViewModel.currentQuestionAnswer
            if (userAnswer == correctAnswer){
                val toast1 = Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_LONG)
                toast1.setGravity(Gravity.TOP,0,0)
                toast1.show()
                correct+=1

                Log.d(TAG, "correct = "+ correct.toString())
            }
            else{
                val toast2 = Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_LONG)
                toast2.setGravity(Gravity.TOP,0,0)
                toast2.show()
            }
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
            //score
            if(count == 4){
                Log.d(TAG, "BEFORE"+count.toString())
                count -=count
                Log.d(TAG, "AFTER"+count.toString())
                val percent = ((correct.toDouble()/4) * 100).toInt()
                val percentage = "Your result is "+ percent+" %"
                val toast3 = Toast.makeText(this, percentage, Toast.LENGTH_LONG)
                toast3.setGravity(Gravity.TOP, 0, 0)
                toast3.show()
                correct -= correct
                Log.d(TAG, correct.toString())
            }
        }



        trueButton.setOnClickListener {
            //do something when you click the true button
            //val toast1 = Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_LONG)
            // toast1 set gravity to the top
            //toast1.show();
            count +=1
            checkAnswer(true);

            Log.d(TAG, count.toString())

        }
        falseButton.setOnClickListener{
            //do something when you click the false button
            //val toast2 = Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_LONG)
            //toast2.show();
            //count +=1
                count += 1
                checkAnswer(false)

            Log.d(TAG, count.toString())

        }
        cheatButton.setOnClickListener {
            //go to the second activity when you click the cheat button
//wrap your second activity into your intent

        }

        fun updateQuestions(){
            val questionTextReId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextReId);
        }

        updateQuestions()

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestions()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
        }
        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestions()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
        }
        previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestions()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)

        }
        previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestions()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)

        }
        // add a new result button

    }
    //***************New, after log processes ***********
    override fun onStart(){
        super.onStart()
        Log.d(TAG,"OnStart is called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"OnPause is called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"OnResume is called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"OnStop is called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"OnDestroy is called")
    }
}