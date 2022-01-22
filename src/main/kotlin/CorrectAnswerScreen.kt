import java.awt.Color
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JPanel

class CorrectAnswerScreen() : JPanel() {
    init{
        val roomIdText1Field = JLabel("Room Id")
        roomIdText1Field.setBounds(200, 10, 200, 40)

        val yourAnswerField = JLabel("Twoj odpowiedź jest")
        yourAnswerField.setBounds(100, 100, 400, 40)
        yourAnswerField.font = Font("Serif", Font.PLAIN, 25)

        val correctAnswerField = JLabel("PRAWIDŁOWA")
        correctAnswerField.setBounds(50, 150, 400, 40)
        correctAnswerField.font = Font("Serif", Font.BOLD, 40)

        layout = null
        background = Color.PINK
        add(roomIdText1Field)
        add(yourAnswerField)
        add(correctAnswerField)
    }
}