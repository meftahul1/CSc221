package com.example.androidquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.androidquiz.ui.theme.AndroidQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicAndroidQuizAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    QuizApp()
                }
            }
        }
    }

    @Composable
    fun QuizApp() {
        var currentQuestionIndex by remember { mutableStateOf(0) }
        var userScore by remember { mutableStateOf(0) }

        val questions = listOf(
            Question("What is the largest ocean on Earth?", listOf("Atlantic Ocean", "Indian Ocean", "Southern Ocean", "Pacific Ocean"), "Pacific Ocean"),
            Question("Who is the author of 'To Kill a Mockingbird'?", listOf("Ernest Hemingway", "Harper Lee", "J.K. Rowling", "George Orwell"), "Harper Lee"),
            Question("Which programming language was created by James Gosling at Sun Microsystems in 1995?", listOf("Java", "Python", "C++", "JavaScript"), "Java"),
            Question("What is the powerhouse of the cell?", listOf("Nucleus", "Mitochondria", "Endoplasmic Reticulum", "Golgi Apparatus"), "Mitochondria"),
            Question("In which year did the first manned moon landing occur?", listOf("1967", "1969", "1971", "1973"), "1969")
        )

        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]

            // UI elements:
            Column {
                Text(text = currentQuestion.question)
                currentQuestion.choices.forEach { choice ->
                    Button(onClick = { onOptionClick(choice, currentQuestion.correctAnswer) }) {
                        Text(choice)
                    }
                }
                Text(text = "Score: $userScore")
            }
        } else {
            // Final score screen displayed
            Text(text = "Quiz completed! Your score: $userScore")
        }
    }

    private fun onOptionClick(selectedAnswer: String, correctAnswer: String) {
        // Implement your logic to check the answer, update the score, and load the next question
        if (selectedAnswer == correctAnswer) { // Correct answer
            userScore += 1 // Increment score
            showSnackbar("Correct!")
        } else { // Incorrect answer
            showSnackbar("Incorrect. The correct answer is $correctAnswer.")
        }

        // Next question
        // Increment the question index
        // Move this logic to where you want to go to the next question
    }

    private fun showSnackbar(message: String) {
        // Implement your Snackbar logic
    }

    data class Question(val question: String, val choices: List<String>, val correctAnswer: String)
}
