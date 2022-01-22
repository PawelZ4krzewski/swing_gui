package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import service.Message
import java.awt.Color
import javax.swing.*

class StartGameScreen(frame: JFrame): JPanel() {
    init{

        //startGamePanel
        val roomIdTextField = JLabel("Room Id")
        roomIdTextField.setBounds(200, 10, 200, 40)
        val startGameButton = JButton("START")
        startGameButton.setBounds(100, 110, 200, 40)

        //Hub Panel
        layout = null
        background = Color.PINK
        add(roomIdTextField)
        add(startGameButton)


        startGameButton.addActionListener {
            service.service.startGame()
        }

        val answers = mutableListOf<String>()
        var question = ""
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                println("Start game screen $it")
                when (it) {
                    is Message.Question -> {
                        question = it.question
                        if (question.isNotEmpty() && answers.size == 4) {
                            frame.navigateTo(ShowQuestionScreen(frame, question, answers))
                            coroutineScope.cancel()
                        }
                    }
                    is Message.Answer -> {
                        answers.add(it.answer)
                        if (question.isNotEmpty() && answers.size == 4) {
                            frame.navigateTo(ShowQuestionScreen(frame, question, answers))
                            coroutineScope.cancel()
                        }
                    }
                    is Message.Error -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>Error message: ${it.exception.localizedMessage}<html>"
                        )
                    }
                }
            }
        }
    }
}