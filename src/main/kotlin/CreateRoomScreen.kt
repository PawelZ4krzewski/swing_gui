import java.awt.Color
import java.awt.Font
import javax.swing.*

class CreateRoomScreen(onNavigate: () -> Unit) : JPanel() {

    init {
        val roomIdLabel = JLabel("IDROOM", SwingConstants.CENTER)
        roomIdLabel.setBounds(50, 0, 300, 50)
        roomIdLabel.font = Font("Serif", Font.BOLD, 25)

        val questionJTextField = JTextField("Question")
        questionJTextField.setBounds(20, 75, 350, 50)

        val answer1JTextField = JTextField("Answer 1")
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

        addQuestionButton.addActionListener({
            println("Dodaje pytanko")
        })

        endCreateRoom.addActionListener({
           onNavigate
        })
    }
}