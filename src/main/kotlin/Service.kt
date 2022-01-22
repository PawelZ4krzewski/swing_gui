import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.net.UnknownHostException

class Service {
    private var socket: Socket? = null
    val _messages = MutableSharedFlow<Message>()
    val messages = _messages.asSharedFlow()

    fun connectTo(ip: String, port: Int) {
        try {
            socket = Socket(ip, port)
            _messages.tryEmit(Message.Connected)
            val inStream = socket?.let {
                BufferedReader(InputStreamReader(it.getInputStream()))
            }
            while (true) {
                if (inStream != null) {
                    val readLine = inStream.readLine()
                    println(readLine)
                }
            }
        } catch (e: UnknownHostException) {
            _messages.tryEmit(Message.Error(e, "Błędny port i/lub adres ip"))
        } catch (e: IOException) {
            _messages.tryEmit(Message.Error(e, "Sprawdź swoje połączenie"))
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
        val paddedQuestionSize = question.length.toString().padStart(3, '0')
        val paddedAnswerOneSize = answerOne.length.toString().padStart(3, '0')
        val paddedAnswerTwoSize = answerTwo.length.toString().padStart(3, '0')
        val paddedAnswerThreeSize = answerThree.length.toString().padStart(3, '0')
        val paddedAnswerFourSize = answerFour.length.toString().padStart(3, '0')
        sendMessage("NEW_QUESTION $paddedQuestionSize$question$paddedAnswerOneSize$answerOne$paddedAnswerTwoSize$answerTwo$paddedAnswerThreeSize$answerThree$paddedAnswerFourSize$answerFour")
    }

    fun createRoom(nickname: String) {

    }

    fun joinRoom(hash: String, nickname: String) {

    }

    fun startGame() {
        sendMessage("START")
    }

    fun Int.toByteArray(): ByteArray =
        ByteArray(4) { i -> (this.toLong() shr (i * 8)).toByte() }

    private fun String.getPaddedSize() = length.toString().padStart(3, '0')

}
