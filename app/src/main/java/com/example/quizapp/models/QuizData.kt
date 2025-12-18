package com.example.quizapp.models

data class Question(
    val id : Int,
    val text: String,
    val answers: List<Answer>,
    val correctAnswerId: Int
)

data class Answer(
    val id: Int,
    val text: String
)

object QuizData{
    val questions = listOf(
        Question(
            1,
            "Какой язык программирования используется для Android разработки?",
            listOf(
                Answer(1, "Java"),
                Answer(2, "Kotlin"),
                Answer(3, "Swift"),
                Answer(4, "C#")
            ),
            2
        ),
        Question(
            2,
            "Какой тип данных в Kotlin используется для хранения целых чисел?",
            listOf(
                Answer(1, "String"),
                Answer(2, "Float"),
                Answer(3, "Int"),
                Answer(4, "Boolean")
            ),
            3
        ),
        Question(
            id = 3,
            text = "Что означает 'val' в Kotlin?",
            answers = listOf(
                Answer(1, "Переменная, которую можно изменить"),
                Answer(2, "Неизменяемая переменная"),
                Answer(3, "Функция"),
                Answer(4, "Класс")
            ),
            correctAnswerId = 2
        ),
        Question(
            id = 4,
            text = "Какой компонент Android отвечает за UI?",
            answers = listOf(
                Answer(1, "Activity"),
                Answer(2, "Service"),
                Answer(3, "BroadcastReceiver"),
                Answer(4, "ContentProvider")
            ),
            correctAnswerId = 1
        ),
        Question(
            id = 5,
            text = "Что такое Composable функция в Jetpack Compose?",
            answers = listOf(
                Answer(1, "Функция для работы с сетью"),
                Answer(2, "Функция для описания UI"),
                Answer(3, "Функция для работы с БД"),
                Answer(4, "Функция для анимаций")
            ),
            correctAnswerId = 2
        )
    )
    val quizTitle = "Kotlin Basics Quiz"
    val quizDescription = "Проверьте свои знания основ Kotlin и Android разработки. " +
            "Вам предстоит ответить на 5 вопросов разной сложности."
}