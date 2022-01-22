package screens

import repaintScreen
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class RoomScreen(onNavigate: () -> Unit) : JPanel() {
    var isAnswered = false

    init{
        val nrQuetionLabel = JLabel("Question 1", SwingConstants.CENTER)
        nrQuetionLabel.setBounds(50, 0, 300, 50)
        nrQuetionLabel.font = Font("Serif", Font.BOLD, 25)

        val questionLabel = JLabel(
            "<html>Pytanie na śniadanie XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDddd<html>",
            SwingConstants.CENTER
        )
        questionLabel.setBounds(50, 40, 300, 150)
        questionLabel.font = Font("Serif", Font.BOLD, 25)

        val answerGroup = JPanel(GridLayout(2, 2, 10, 10))
        answerGroup.setBounds(45, 250, 300, 300)
        answerGroup.background = Color.BLACK

        val answer1JButton = JButton("A")
        answer1JButton.background = Color.RED
        answer1JButton.setSize(Dimension(75, 75))
        answer1JButton.font = Font("Serif", Font.BOLD, 50)

        val answer2JButton = JButton("B")
        answer2JButton.background = Color.GREEN
        answer2JButton.setSize(Dimension(75, 75))
        answer2JButton.font = Font("Serif", Font.BOLD, 50)

        val answer3JButton = JButton("C")
        answer3JButton.background = Color.YELLOW
        answer3JButton.setSize(Dimension(75, 75))
        answer3JButton.font = Font("Serif", Font.BOLD, 50)

        val answer4JButton = JButton("D")
        answer4JButton.background = Color.BLUE
        answer4JButton.setSize(Dimension(75, 75))
        answer4JButton.font = Font("Serif", Font.BOLD, 50)

        layout = null
        background = Color.MAGENTA
        add(nrQuetionLabel)
        add(questionLabel)
        add(answerGroup)
        answerGroup.add(answer1JButton)
        answerGroup.add(answer2JButton)
        answerGroup.add(answer3JButton)
        answerGroup.add(answer4JButton)

        answer1JButton.addActionListener {
            if (!isAnswered) {
                answer2JButton.background = Color.GRAY
                answer3JButton.background = Color.GRAY
                answer4JButton.background = Color.GRAY
                isAnswered = true
                repaintScreen()
            }
        }
        answer2JButton.addActionListener {
            if (!isAnswered) {
                answer1JButton.background = Color.GRAY
                answer3JButton.background = Color.GRAY
                answer4JButton.background = Color.GRAY
                isAnswered = true
                repaintScreen()
            }
        }
        answer3JButton.addActionListener {
            if (!isAnswered) {
                answer2JButton.background = Color.GRAY
                answer1JButton.background = Color.GRAY
                answer4JButton.background = Color.GRAY
                isAnswered = true
                repaintScreen()
            }
        }
        answer4JButton.addActionListener {
            if (!isAnswered) {
                answer2JButton.background = Color.GRAY
                answer3JButton.background = Color.GRAY
                answer1JButton.background = Color.GRAY
                isAnswered = true
                repaintScreen()
            }
        }
    }
}