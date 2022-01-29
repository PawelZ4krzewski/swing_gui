package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import service.Message
import java.awt.Color
import java.awt.Font
import javax.swing.*

class CreateRoomScreen(frame: JFrame) : JPanel() {
    val questions = mutableListOf<Question>()

    init {
        val roomIdLabel = JLabel("IDROOM", SwingConstants.CENTER)
        roomIdLabel.setBounds(50, 0, 300, 50)
        roomIdLabel.font = Font("Serif", Font.BOLD, 25)

        val questionJTextField = JTextField("Question")
        questionJTextField.setBounds(20, 75, 350, 50)

        val answer1JTextField = JTextField("Correct Answer")
        answer1JTextField.setBounds(20, 125, 350, 50)

        val answer2JTextField = JTextField("Answer 2")
        answer2JTextField.setBounds(20, 175, 350, 50)

        val answer3JTextField = JTextField("Answer 3")
        answer3JTextField.setBounds(20, 225, 350, 50)

        val answer4JTextField = JTextField("Answer 4")
        answer4JTextField.setBounds(20, 275, 350, 50)

        val addQuestionButton = JButton("Add Question")
        addQuestionButton.setBounds(100, 400, 200, 50)

        val endCreateRoom = JButton("Zakoncz")
        endCreateRoom.setBounds(100, 500, 200, 50)

        layout = null
        background = Color.YELLOW
        add(roomIdLabel)
        add(questionJTextField)
        add(answer1JTextField)
        add(answer2JTextField)
        add(answer3JTextField)
        add(answer4JTextField)
        add(addQuestionButton)
        add(endCreateRoom)

        addQuestionButton.addActionListener {
            questions.add(
                Question(
                    questionJTextField.text,
                    listOf(answer1JTextField, answer2JTextField, answer3JTextField, answer4JTextField).map { it.text },
                )
            )
            questionJTextField.text = "Question"
            answer1JTextField.text = "Correct Answer"
            answer2JTextField.text = "Answer 2"
            answer3JTextField.text = "Answer 3"
            answer4JTextField.text = "Answer 4"
        }

        endCreateRoom.addActionListener {
            print(questions)
            service.service.createRoom("PAWEL CZEMU NIE ZROBIELS TEXT FIELDA NA TO")
            questions.forEach {
                service.service.addQuestion(it.question, it.answers[0], it.answers[1], it.answers[2], it.answers[3])
            }
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                when (it) {
                    is Message.Created -> {
                        frame.navigateTo(StartGameScreen(frame, it.hash))
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