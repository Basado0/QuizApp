package com.example.quizapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class QuizUiState(
    val screen: QuizScreen = QuizScreen.START,
    val currentQuestionIndex: Int = 0,
    val selectedAnswerId : Int? = null,
    val correctAnswers : Int = 0,
    val isAnswerSelected : Boolean = false,
)

enum class QuizScreen{
    START, QUESTION, RESULT
}

class QuizViewModel() : ViewModel(){
    var uiState by mutableStateOf(QuizUiState())
    val questions: List<Question> = QuizData.questions

    fun startQuiz(){
        uiState = QuizUiState(
            screen = QuizScreen.QUESTION,
            currentQuestionIndex = 0,
            selectedAnswerId = null,
            correctAnswers = 0,
            isAnswerSelected = false
        )
    }

    fun selectAnswer(answerId: Int){
        uiState = uiState.copy(
            selectedAnswerId = answerId,
            isAnswerSelected = true
        )
    }

    fun nextQuestion(){
        // Проверяем правильность текущего ответа
        val currentQuestion: Question = questions[uiState.currentQuestionIndex]
        val isCorrect = uiState.selectedAnswerId == currentQuestion.correctAnswerId
        val newCorrectAnswers = uiState.correctAnswers + if (isCorrect) 1 else 0
        // Если есть еще вопросы
        if (uiState.currentQuestionIndex < questions.size-1 ){
            uiState = uiState.copy(
                currentQuestionIndex = uiState.currentQuestionIndex + 1,
                selectedAnswerId = null,
                correctAnswers = newCorrectAnswers,
                isAnswerSelected = false
            )
        }
        else {
            uiState = uiState.copy(
                screen = QuizScreen.RESULT,
                correctAnswers = newCorrectAnswers
            )
        }
    }

    fun restartQuiz() {
        uiState = QuizUiState()
    }

    val currentQuestion: Question
        get() = questions[uiState.currentQuestionIndex]

    fun getTotalQuestions(): Int = questions.size

    fun getPercentage(): Float {
        val total = getTotalQuestions()
        if (total == 0) return 0f
        return (uiState.correctAnswers.toFloat() / total) * 100
    }

    fun getResultComment(): String {
        val percentage = getPercentage()
        return when {
            percentage < 50 -> "Есть куда расти! Попробуйте ещё раз."
            percentage < 80 -> "Хороший результат! Но можно лучше."
            else -> "Отличный результат! Вы отлично знаете тему!"
        }
    }
}