package com.example.and102_project1

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.text.color


class MainActivity : AppCompatActivity() {

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    private fun checkGuess(guess: String): SpannableStringBuilder {
        val result = SpannableStringBuilder()
        for (i in 0 until minOf(guess.length, wordToGuess.length)) {
            val letter = guess[i]
            val actualLetter = wordToGuess[i]
            val color = when {
                letter == actualLetter -> Color.GREEN // Correct letter
                letter in wordToGuess -> Color.YELLOW // Incorrect position
                else -> Color.RED // Incorrect letter
            }

            val coloredLetter = SpannableStringBuilder()
                .color(color) { append(letter.toString()) }
            result.append(coloredLetter)
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //defining by text views and buttons as well as the edit text input
        var count = 0
        var streak = 0
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.et_simple)
        val textView7 = findViewById<TextView>(R.id.textView7)
        val textView9 = findViewById<TextView>(R.id.textView9)
        val textView11 = findViewById<TextView>(R.id.textView11)
        val textView8 = findViewById<TextView>(R.id.textView8)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val textView12 = findViewById<TextView>(R.id.textView12)
        val textView13 = findViewById<TextView>(R.id.textView13)
        val textView15 = findViewById<TextView>(R.id.textView15)


        //creating a loop of text views so that I can loop through them
        var list = mutableListOf(textView7, textView9, textView11)
        var list2 = mutableListOf(textView8, textView10, textView12)
        //defining index values to keep track of the index when I loop through them
        var textIndex = 0
        var checkIndex = 0



        button.setOnClickListener{
            //this takes the edit text input and makes it a string
            val strValue: String = editText.text.toString()

            if (strValue.matches(Regex("[a-zA-Z0-9]+"))) {
                // String contains only alphanumeric characters
                Toast.makeText(this, "String is alphanumeric", Toast.LENGTH_SHORT).show()
            }


            //assigning the strValue input to the appropriate index
            list[textIndex].text = strValue
            //updating the index
            textIndex++
            //calling the checkGuess function to check whether the guessed word is correct
            var tGuess = checkGuess(strValue)
            list2[checkIndex].text = tGuess
            it.hideKeyboard()
            editText.text.clear()


            count++
            checkIndex++

            // if maximum amount of tries have reached or the user guessed the right word, display the word
            if (count == 3 || strValue == wordToGuess){
                textView13.text = wordToGuess
                textView13.visibility = View.VISIBLE
                button.isEnabled = false

                if (strValue == wordToGuess){
                    streak++
                    textView15.text = streak.toString()

                }

            }


        }


    }
}