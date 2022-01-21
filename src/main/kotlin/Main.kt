import java.awt.*
import javax.swing.*

val frame = JFrame("Hello, Kotlin/Swing")
val hubPanel = JPanel(null)
val roomPanel = JPanel(null)
val createRoomPanel = JPanel(null)
val startGamePanel = JPanel(null)
val correctAnswerPanel = JPanel(null)
val showQuestionPanel = JPanel(null)

var isAnswered = false

fun connectToServer(server: String, port: String) {
    println("Server: " + server + " port: " + port)
    //TODO connect to the server

}

fun startGUI() {

    frame.layout = null
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(Dimension(400, 600))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
}

fun hubScreen() {

    //hubPanel
    val roomIdTextField = JTextField("Room Id")
    roomIdTextField.setBounds(40, 10, 200, 40)
    val connectToRoomButton = JButton("Connect to room")
    connectToRoomButton.setBounds(40, 60, 200, 40)
    val createRoomButton = JButton("Create Room")
    createRoomButton.setBounds(40, 110, 200, 40)

    //Hub Panel
    hubPanel.background = Color.BLUE
    hubPanel.add(roomIdTextField)
    hubPanel.add(connectToRoomButton)
    hubPanel.add(createRoomButton)


    createRoomButton.addActionListener({
        frame.contentPane.removeAll()
        frame.repaint()
        frame.setContentPane(createRoomPanel)
        repaintScreen()
    })

    connectToRoomButton.addActionListener({
        frame.contentPane.removeAll()
        frame.setContentPane(roomPanel)
        repaintScreen()
    })

}

fun roomScreen() {
    //Room panel
    isAnswered = false
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

    roomPanel.background = Color.MAGENTA
    roomPanel.add(nrQuetionLabel)
    roomPanel.add(questionLabel)
    roomPanel.add(answerGroup)
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

fun createRoomScreen() {

    val roomIdLabel = JLabel("IDROOM", SwingConstants.CENTER)
    roomIdLabel.setBounds(50, 0, 300, 50)
    roomIdLabel.font = Font("Serif", Font.BOLD, 25)

    val questionJTextField = JTextField("Question")
    questionJTextField.setBounds(20, 75, 350, 50)

    createRoomPanel.addTextField("Question", 20, 75, 350, 50)

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

    createRoomPanel.background = Color.YELLOW
    createRoomPanel.add(roomIdLabel)
    createRoomPanel.add(questionJTextField)
    createRoomPanel.add(answer1JTextField)
    createRoomPanel.add(answer2JTextField)
    createRoomPanel.add(answer3JTextField)
    createRoomPanel.add(answer4JTextField)
    createRoomPanel.add(addQuestionButton)
    createRoomPanel.add(endCreateRoom)

    addQuestionButton.addActionListener({
        println("Dodaje pytanko")
    })

    endCreateRoom.addActionListener({
        frame.contentPane.removeAll()
        frame.setContentPane(startGamePanel)
        repaintScreen()
    })

}


fun startGameScreen() {

    //startGamePanel
    val roomIdTextField = JLabel("Room Id")
    roomIdTextField.setBounds(200, 10, 200, 40)
    val startGameButton = JButton("START")
    startGameButton.setBounds(100, 110, 200, 40)

    //Hub Panel
    startGamePanel.background = Color.PINK
    startGamePanel.add(roomIdTextField)
    startGamePanel.add(startGameButton)


    startGameButton.addActionListener({
        frame.contentPane.removeAll()
        frame.repaint()
        frame.setContentPane(showQuestionPanel)
        repaintScreen()
    })

}

fun correctAnswerScreen() {

    val roomIdText1Field = JLabel("Room Id")
    roomIdText1Field.setBounds(200, 10, 200, 40)

    val yourAnswerField = JLabel("Twoj odpowiedź jest")
    yourAnswerField.setBounds(100, 100, 400, 40)
    yourAnswerField.font = Font("Serif", Font.PLAIN, 25)

    val correctAnswerField = JLabel("PRAWIDŁOWA")
    correctAnswerField.setBounds(50, 150, 400, 40)
    correctAnswerField.font = Font("Serif", Font.BOLD, 40)

    correctAnswerPanel.background = Color.PINK
    correctAnswerPanel.add(roomIdText1Field)
    correctAnswerPanel.add(yourAnswerField)
    correctAnswerPanel.add(correctAnswerField)
}

fun showQuestionScreen(question: String, answerA: String, answerB: String, answerC: String, answerD: String) {
//    frame.contentPane.removeAll()

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

    showQuestionPanel.add(roomIdSQLabel)
    showQuestionPanel.add(questionShowJTextField)
    showQuestionPanel.add(answer1ShowJTextField)
    showQuestionPanel.add(answer2ShowJTextField)
    showQuestionPanel.add(answer3ShowJTextField)
    showQuestionPanel.add(answer4ShowJTextField)
    showQuestionPanel.add(timerJTextField)
}

fun repaintScreen() {
    frame.invalidate()
    frame.validate()
    frame.repaint()
}

fun main(args: Array<String>) {

    startGUI()
    frame.contentPane = ConnectScreen {
        navigateTo(createRoomPanel)
//        navigateTo(hubPanel)
    }
    repaintScreen()
    hubScreen()
//    roomScreen()
    createRoomScreen()
//    startGameScreen()
//    correctAnswerScreen()
//    for (i in 1..10){
//        frame.contentPane.removeAll()
//        showQuestionScreen("Question $i", "Answer A $i", "Answer B $i", "Answer C $i", "Answer D $i")
//        frame.setContentPane(showQuestionPanel)
//        repaintScreen()
//        Thread.sleep(2000)
//        frame.contentPane.removeAll()
//        correctAnswerScreen()
//        frame.setContentPane(correctAnswerPanel)
//        repaintScreen()
//        Thread.sleep(2000)
//    }


    //connectionPanel
//    val adresTextField = JTextField("Adres")
//    adresTextField.setBounds(40, 10, 200, 40)
//    val portTextField = JTextField("Port")
//    portTextField.setBounds(40, 60, 200, 40)
//    val connectButton = JButton("connect")
//    connectButton.setBounds(40, 110, 200, 40)

//    connectionPanel.background = Color.RED
//    connectionPanel.add(adresTextField, BorderLayout.CENTER)
//    connectionPanel.add(portTextField, BorderLayout.CENTER)
//    connectionPanel.add(connectButton, BorderLayout.CENTER)
//
//    frame.setContentPane(connectionPanel)
//
//    frame.invalidate()
//    frame.validate()
//    frame.repaint()


}

private fun navigateTo(panel: JPanel) {
    frame.contentPane.removeAll()
    frame.contentPane = panel
    repaintScreen()
}