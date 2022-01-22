package screens

import java.awt.Font
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class ShowQuestionScreen(question: String, answerA: String, answerB: String, answerC: String, answerD: String) : JPanel() {

    init{
        val roomIdSQLabel = JLabel("IDROOM", SwingConstants.CENTER)
        roomIdSQLabel.setBounds(50, 0, 300, 50)
        roomIdSQLabel.font = Font("Serif", Font.BOLD, 25)

        val questionShowJTextField = JLabel("Question: " + question)
        questionShowJTextField.setBounds(20, 75, 350, 50)
        questionShowJTextField.font = Font("Serif", Font.BOLD, 25)

        val answer1ShowJTextField = JLabel("A: " + answerA)
        answer1ShowJTextField.setBounds(20, 125, 350, 50)
        answer1ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer2ShowJTextField = JLabel("B: " + answerB)
        answer2ShowJTextField.setBounds(20, 175, 350, 50)
        answer2ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer3ShowJTextField = JLabel("C: " + answerC)
        answer3ShowJTextField.setBounds(20, 225, 350, 50)
        answer3ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val answer4ShowJTextField = JLabel("D: " + answerD)
        answer4ShowJTextField.setBounds(20, 275, 350, 50)
        answer4ShowJTextField.font = Font("Serif", Font.BOLD, 20)

        val timerJTextField = JLabel("30")
        timerJTextField.setBounds(175, 400, 350, 50)
        timerJTextField.font = Font("Serif", Font.BOLD, 40)

        layout = null
        add(roomIdSQLabel)
        add(questionShowJTextField)
        add(answer1ShowJTextField)
        add(answer2ShowJTextField)
        add(answer3ShowJTextField)
        add(answer4ShowJTextField)
        add(timerJTextField)
    }

}