package service

import java.lang.Exception

sealed class Message {
    object Disconnected: Message()
    data class Error(val exception: Exception, val message: String): Message()
    object Connected : Message()
    object Started : Message()
    object Joined : Message()
    object Ended : Message()
    data class Created(val hash: String) : Message()
    data class Question(val question: String) : Message()
    data class Answer(val answer: String, val id: Int) : Message()
    data class CorrectAnswer(val id: Int) : Message()
    data class Ranking(val name: String) : Message()
}