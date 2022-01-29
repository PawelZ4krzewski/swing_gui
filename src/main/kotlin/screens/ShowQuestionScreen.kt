package screens

import kotlinx.coroutines.*
import navigateTo
import service.Message
import java.awt.Color
import java.awt.Font
import javax.swing.*

class ShowQuestionScreen(frame: JFrame, question: String, answers: List<String>) : JPanel() {
    private val length = 20
    init {

        val questionShowJTextField = JLabel("Question: $question")
        questionShowJTextField.setBounds(20, 75, 350, 50)
        questionShowJTextField.font = Font("Serif", Font.BOLD, 25)

        val answer1ShowJTextField = JLabel("A: ${answers[0]}")
        answer1ShowJTextField.setBounds(20, 125, 350, 50)
        answer1ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer2ShowJTextField = JLabel("B: ${answers[1]}")
        answer2ShowJTextField.setBounds(20, 175, 350, 50)
        answer2ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer3ShowJTextField = JLabel("C: ${answers[2]}")
        answer3ShowJTextField.setBounds(20, 225, 350, 50)
        answer3ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer4ShowJTextField = JLabel("D: ${answers[3]}")
        answer4ShowJTextField.setBounds(20, 275, 350, 50)
        answer4ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val timerJTextField = JLabel("$length")
        timerJTextField.setBounds(175, 400, 350, 50)
        timerJTextField.font = Font("Serif", Font.BOLD, 40)

        val answersTextField = listOf(answer1ShowJTextField, answer2ShowJTextField, answer3ShowJTextField, answer4ShowJTextField)
        layout = null
        add(questionShowJTextField)
        add(answer1ShowJTextField)
        add(answer2ShowJTextField)
        add(answer3ShowJTextField)
        add(answer4ShowJTextField)
        add(timerJTextField)


        val answersTemp = mutableListOf<String>()
        var questionTemp = ""
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            repeat(length) {
                delay(1000)
                timerJTextField.text = (length - 1 - it).toString()
            }
        }
        coroutineScope.launch {
            service.service.messages.collect {
                println("Show question screen $it")
                when (it) {
                    is Message.Question -> {
                        questionTemp = it.question
                        if (questionTemp.isNotEmpty() && answersTemp.size == 4) {
                            frame.navigateTo(ShowQuestionScreen(frame, questionTemp, answersTemp))
                            coroutineScope.cancel()
                        }
                    }
                    is Message.Answer -> {
                        answersTemp.add(it.answer)
                        if (questionTemp.isNotEmpty() && answersTemp.size == 4) {
                            frame.navigateTo(ShowQuestionScreen(frame, questionTemp, answersTemp))
                            coroutineScope.cancel()
                        }
                    }
                    is Message.CorrectAnswer -> {
                        answersTextField[it.id].foreground = Color.GREEN
                    }
                    is Message.Ended -> {
                        frame.navigateTo(RankingScreen(frame))
                        coroutineScope.cancel()
                    }
                    is Message.Disconnected -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>You've been disconnected<html>"
                        )
                        frame.navigateTo(ConnectScreen(frame))
                        coroutineScope.cancel()
                    }
                }
            }
        }

    }
}