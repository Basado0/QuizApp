package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.models.QuizScreen
import com.example.quizapp.models.QuizViewModel
import com.example.quizapp.screens.QuestionScreen
import com.example.quizapp.screens.ResultScreen
import com.example.quizapp.screens.StartScreen
import com.example.quizapp.ui.theme.QuizAppTheme



class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App(){
    val viewModel: QuizViewModel = viewModel()
    val uiState = viewModel.uiState

    when(uiState.screen){
        QuizScreen.START -> StartScreen (
            { viewModel.startQuiz() }
        )

        QuizScreen.QUESTION -> QuestionScreen(
            currentQuestion = viewModel.currentQuestion,
            currentQuestionIndex = uiState.currentQuestionIndex,
            totalQuestions = viewModel.getTotalQuestions(),
            selectedAnswerId = uiState.selectedAnswerId,
            onAnswerSelected = {answerId -> viewModel.selectAnswer(answerId)},
            onNextClick = {viewModel.nextQuestion()},
            isAnswerSelected = uiState.isAnswerSelected
        )

        QuizScreen.RESULT -> ResultScreen(
            correctAnswers = uiState.correctAnswers,
            totalQuestions = viewModel.getTotalQuestions(),
            percentage = viewModel.getPercentage(),
            comment = viewModel.getResultComment(),
            onRestartClick = {viewModel.restartQuiz()}
        )
    }
}