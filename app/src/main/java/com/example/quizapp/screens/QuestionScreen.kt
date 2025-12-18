package com.example.quizapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.models.Question

@Composable
fun QuestionScreen(
    currentQuestion: Question,
    currentQuestionIndex: Int,
    totalQuestions: Int,
    selectedAnswerId: Int?,
    onAnswerSelected: (Int) -> Unit,
    onNextClick: () -> Unit,
    isAnswerSelected: Boolean
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Просто текст с номером вопроса
        Text(
            text = "Вопрос ${currentQuestionIndex + 1} из $totalQuestions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        // Текст вопроса
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(
                text = currentQuestion.text,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Варианты ответов
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            currentQuestion.answers.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onAnswerSelected(answer.id) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedAnswerId == answer.id,
                        onClick = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = answer.text,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Кнопка Дальше
        Button(
            onClick = onNextClick,
            enabled = isAnswerSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = if (currentQuestionIndex < totalQuestions) "Дальше" else "Завершить",
                fontSize = 16.sp
            )
        }
    }
}