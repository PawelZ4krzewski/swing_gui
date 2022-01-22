package service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.net.UnknownHostException

val service = Service()

class Service {
    private var socket: Socket? = null
    val _messages = MutableSharedFlow<Message>()
    val messages = _messages.asSharedFlow()

    fun connectTo(ip: String, port: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                socket = Socket(ip, port)
                _messages.emit(Message.Connected)
                val inStream = socket?.let {
                    BufferedReader(InputStreamReader(it.getInputStream()))
                }
                while (true) {
                    if (inStream != null) {
                        val readLine = inStream.readLine()
                        println(readLine)
                        when {
                            readLine.startsWith("CREATED ") -> {
                                _messages.emit(Message.Created(readLine.drop("CREATED ".length)))
                            }
                        }
                    }
                }
            } catch (e: UnknownHostException) {
                _messages.emit(Message.Error(e, "Błędny port i/lub adres ip"))
            } catch (e: IOException) {
                _messages.emit(Message.Error(e, "Sprawdź swoje połączenie"))
            }
        }
    }

    fun sendAnswer(questionId: Int, answerId: Int) {
        val paddedQuestionId = questionId.toString().padStart(3, '0')
        val message = "ANSWER $paddedQuestionId $answerId"
        sendMessage(message)
    }

    private fun sendMessage(message: String) {
        try {
            socket?.getOutputStream()?.write(message.length.toByteArray() + message.toByteArray())
        } catch (e: IOException) {
            _messages.tryEmit(Message.Error(e, "Sprawdź swoje połączenie"))
        }
    }

    fun addQuestion(question: String, answerOne: String, answerTwo: String, answerThree: String, answerFour: String) {
        val paddedQuestionSize = question.getPaddedSize(3)
        val paddedAnswerOneSize = question.getPaddedSize(3)
        val paddedAnswerTwoSize = question.getPaddedSize(3)
        val paddedAnswerThreeSize = question.getPaddedSize(3)
        val paddedAnswerFourSize = question.getPaddedSize(3)
        sendMessage("NEW_QUESTION $paddedQuestionSize$question$paddedAnswerOneSize$answerOne$paddedAnswerTwoSize$answerTwo$paddedAnswerThreeSize$answerThree$paddedAnswerFourSize$answerFour")
    }

    fun createRoom(nickname: String) {
        sendMessage("CREATE_ROOM $nickname")
    }

    fun joinRoom(hash: String, nickname: String) {
        sendMessage("JOIN_ROOM $hash $nickname")
    }

    fun startGame() {
        sendMessage("START")
    }

    fun Int.toByteArray(): ByteArray =
        ByteArray(4) { i -> (this.toLong() shr (i * 8)).toByte() }

    private fun String.getPaddedSize(padSize: Int) = length.toString().padStart(padSize, '0')

}
