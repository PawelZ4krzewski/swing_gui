import java.awt.*
import javax.swing.*
import javax.swing.BorderFactory.createEmptyBorder

val frame = JFrame("Hello, Kotlin/Swing")
//val hubPanel = JPanel(null)
//val roomPanel = JPanel(null)
//val createRoomPanel = JPanel(null)
//val startGamePanel = JPanel(null)
val correctAnswerPanel = JPanel(null)
val showQuestionPanel = JPanel(null)

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

fun repaintScreen() {
    frame.invalidate()
    frame.validate()
    frame.repaint()
}

fun main(args: Array<String>) {

    startGUI()
    val roomScreen = RoomScreen {}
    val startGameScreen = StartGameScreen{navigateTo(roomScreen)}
    val corectAnswer = CorrectAnswerScreen()
    val showQuestion =ShowQuestionScreen(
        "Question",
        "Answer A",
        "Answer B ",
        "Answer C ",
        "Answer D ",
        )
    val createRoom = CreateRoomScreen { navigateTo(startGameScreen) }
    val hubScreen = HubScreen({navigateTo(createRoom)},{navigateTo(createRoom)})

    frame.contentPane = ConnectScreen {
        navigateTo(hubScreen)
//        navigateTo(hubPanel)
    }


    repaintScreen()
//    hubScreen()
//    roomScreen()
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