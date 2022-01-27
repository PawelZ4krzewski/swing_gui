package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import repaintScreen
import service.Message
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*

class RoomScreen(frame: JFrame, id: Int) : JPanel() {
    var selected = -1
    init {
        val nrQuetionLabel = JLabel("Question $id", SwingConstants.CENTER)
        nrQuetionLabel.setBounds(50, 0, 300, 50)
        nrQuetionLabel.font = Font("Serif", Font.BOLD, 25)


        val answerGroup = JPanel(GridLayout(2, 2, 10, 10))
        answerGroup.setBounds(45, 250, 300, 300)
        answerGroup.background = Color.BLACK

        val answer1JButton = JButton("A")
        answer1JButton.isOpaque = true
        answer1JButton.background = Color.CYAN
        answer1JButton.size = Dimension(75, 75)
        answer1JButton.font = Font("Serif", Font.BOLD, 50)

        val answer2JButton = JButton("B")
        answer2JButton.isOpaque = true
        answer2JButton.background = Color.PINK
        answer2JButton.size = Dimension(75, 75)
        answer2JButton.font = Font("Serif", Font.BOLD, 50)

        val answer3JButton = JButton("C")
        answer3JButton.isOpaque = true
        answer3JButton.background = Color.orange
        answer3JButton.size = Dimension(75, 75)
        answer3JButton.font = Font("Serif", Font.BOLD, 50)

        val answer4JButton = JButton("D")
        answer4JButton.isOpaque = true
        answer4JButton.background = Color.BLUE
        answer4JButton.size = Dimension(75, 75)
        answer4JButton.font = Font("Serif", Font.BOLD, 50)

        layout = null
        background = Color.WHITE
        add(nrQuetionLabel)
        add(answerGroup)
        answerGroup.add(answer1JButton)
        answerGroup.add(answer2JButton)
        answerGroup.add(answer3JButton)
        answerGroup.add(answer4JButton)

        val buttons = listOf(answer1JButton, answer2JButton, answer3JButton, answer4JButton)
        buttons.forEachIndexed { index, button ->
            button.addActionListener {
                selected = index
                buttons.filterNot { it == button }.forEach {
                    it.background = Color.GRAY
                }
                buttons.forEach { it.isEnabled = false }
                service.service.sendAnswer(id, index)
                repaintScreen()
            }
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                println("Room screen $it")
                when (it) {
                    is Message.Question -> {
                        frame.navigateTo(RoomScreen(frame, id + 1))
                        coroutineScope.cancel()
                    }
                    is Message.CorrectAnswer -> {
                        buttons.forEach { it.isEnabled = false }
                        buttons.forEach {
                            it.background = Color.GRAY
                        }
                        when (selected) {
                            it.id -> {
                                buttons[selected].background = Color.GREEN
                            }
                            -1 -> {
                                buttons[it.id].background = Color.GREEN
                            }
                            else -> {
                                buttons[it.id].background = Color.GREEN
                                buttons[selected].background = Color.RED
                            }
                        }
                    }
                    is Message.HostDisconnected -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>Host has disconnected<html>"
                        )
                        frame.navigateTo(HubScreen(frame))
                        coroutineScope.cancel()
                    }
                    is Message.Ended -> {
                        frame.navigateTo(RankingScreen(frame))
                        coroutineScope.cancel()
                    }
                    is Message.Disconnected, is Message.Error -> {
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