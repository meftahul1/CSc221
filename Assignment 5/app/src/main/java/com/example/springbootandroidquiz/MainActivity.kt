package com.example.springbootandroidquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.springbootandroidquiz.data.QuizApi
import com.example.springbootandroidquiz.data.models.Quiz
import com.example.springbootandroidquiz.ui.theme.SpringBootAndroidQuizTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val quizApi: QuizApi by lazy {
        QuizApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpringBootAndroidQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizContent()
                }
            }
        }
    }

    @Composable
    fun QuizContent() {
        var quizzes by remember { mutableStateOf<List<Quiz>>(emptyList()) }
        var selectedQuiz by remember { mutableStateOf<Quiz?>(null) }

        LaunchedEffect(true) {
            fetchQuizzes()
        }

        if (selectedQuiz != null) {
            QuizQuestions(selectedQuiz!!)
        } else {
            QuizList(quizzes) { selectedQuiz = it }
        }
    }

    @Composable
    fun QuizList(quizzes: List<Quiz>, onQuizSelected: (Quiz) -> Unit) {
        LazyColumn {
            items(quizzes) { quiz ->
                QuizListItem(quiz = quiz, onClick = { onQuizSelected(quiz) })
            }
        }
    }

    @Composable
    fun QuizListItem(quiz: Quiz, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Quiz ID: ${quiz.id}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Title: ${quiz.title}", style = MaterialTheme.typography.subtitle1)
            }
        }
    }

    @Composable
    fun QuizQuestions(quiz: Quiz) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Quiz: ${quiz.title}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))

            quiz.questions.forEachIndexed { index, question ->
                QuizQuestionItem(question = question, onAnswerSelected = {
                    // Handle answer selection
                    // For example, show feedback to the user
                    // You can customize this part based on your app's logic
                    // For simplicity, we'll just print the selected answer
                    println("Question ${index + 1} - Selected Answer: $it")
                })
                Spacer(modifier = Modifier.height(16.dp))
            }

            // You can add a button here to submit the quiz or navigate back to the quiz list
        }
    }

    @Composable
    fun QuizQuestionItem(question: Question, onAnswerSelected: (Int) -> Unit) {
        var selectedAnswer by remember { mutableStateOf(-1) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable { /* Handle click if needed */ }
                .padding(16.dp)
        ) {
            Text(text = question.text, style = MaterialTheme.typography.subtitle1)

            Spacer(modifier = Modifier.height(16.dp))

            question.options.forEachIndexed { index, option ->
                QuizAnswerItem(
                    option = option,
                    isSelected = index == selectedAnswer,
                    onClick = { selectedAnswer = index; onAnswerSelected(index) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun QuizAnswerItem(option: String, isSelected: Boolean, onClick: () -> Unit) {
        val backgroundColor = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.background
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(backgroundColor)
                .clickable { onClick() }
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = option,
                style = MaterialTheme.typography.body1,
                color = if (isSelected) Color.White else LocalContentColor.current
            )
        }
    }

    private fun fetchQuizzes() {
        val call = quizApi.getQuizzes()
        call.enqueue(object : Callback<List<Quiz>> {
            override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
                if (response.isSuccessful) {
                    quizzes = response.body() ?: emptyList()
                } else {
                    // Handle error
                    println("Failed to fetch quizzes. Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                // Handle failure
                println("Failed to fetch quizzes: ${t.message}")
            }
        })
    }
}

private fun Any.forEachIndexed(any: Any) {
    TODO("Not yet implemented")
}
