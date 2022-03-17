package com.example.hellokittyquiz

import android.R.attr.checked
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var summaryButton: Button
    private lateinit var startButton: Button

    private val quizViewModel:QuizViewModel by lazy{
        ViewModelProviders.of(this).get(QuizViewModel::class.java)

    }
    private val TAG = "MainActivity"
    private var count = 0
    private var correct = 0
    val summaryList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val QuizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "We got a QuizViewModel!")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)
        summaryButton = findViewById(R.id.summary_button)
        startButton = findViewById(R.id.start_button)

        summaryButton.setEnabled(false)
        cheatButton.setEnabled(false)
        trueButton.setEnabled(false)
        falseButton.setEnabled(false)
        nextButton.setEnabled(false)
        previousButton.setEnabled(false)


        fun checkAnswer(userAnswer: Boolean) {
            val correctAnswer = quizViewModel.currentQuestionAnswer
            if (userAnswer == correctAnswer) {
                val toast1 = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG)
                toast1.setGravity(Gravity.TOP, 0, 0)
                toast1.show()
                correct += 1
                Log.d(TAG, "correct = " + correct.toString())

                summaryList.add("Question" + count + ": Correct")
            } else {
                val toast2 = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG)
                toast2.setGravity(Gravity.TOP, 0, 0)
                toast2.show()
                summaryList.add("Question" + count + ": Incorrect")
            }
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
            //score
            if (count == 4) {
                summaryButton.setEnabled(true)
                nextButton.setEnabled(false)
                previousButton.setEnabled(false)
                cheatButton.setEnabled(false)
            }

        }



        trueButton.setOnClickListener {
            //do something when you click the true button
            //val toast1 = Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_LONG)
            // toast1 set gravity to the top
            //toast1.show();
            count += 1
            checkAnswer(true);

            Log.d(TAG, count.toString())

        }
        falseButton.setOnClickListener {
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

        fun updateQuestions() {
            val questionTextReId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextReId);
        }

        //updateQuestions()
        startButton.setOnClickListener {
            val start_text = "Begin!"
            val toast5 = Toast.makeText(this, start_text, Toast.LENGTH_LONG)
            toast5.setGravity(Gravity.TOP, 0, 0)
            toast5.show()
            quizViewModel.moveToNext()
            updateQuestions()
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
            nextButton.setEnabled(true)
            previousButton.setEnabled(true)
            startButton.setEnabled(false)

        }


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

        summaryButton.setOnClickListener {
            count -= count
            Log.d(TAG, "AFTER" + count.toString())
            val percent = ((correct.toDouble() / 4) * 100).toInt()
            val percentage = "Your result is " + percent + " %"

            var summaryText = ""
            summaryText =
                percentage + "\n" + "\n" + summaryList.get(0) + "\n" + summaryList.get(1) + "\n" + summaryList.get(2) + "\n" + summaryList.get(
                    3
                )
            val toast4 = Toast.makeText(this, summaryText, Toast.LENGTH_LONG)
            toast4.show()

            summaryButton.setEnabled(false)

        }
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