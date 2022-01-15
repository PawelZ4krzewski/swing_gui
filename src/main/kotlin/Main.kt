import java.awt.*
import javax.swing.*
import javax.swing.BorderFactory.createEmptyBorder

fun connectToServer(server: String, port: String) {
    println("Server: " +server +" port: "+port)
    //TODO connect to the server

}

fun connectScreen(){
    //connectionPanel
    val adresTextField = JTextField("Adres")
    adresTextField.setBounds(40, 10, 200, 40)
    val portTextField = JTextField("Port")
    portTextField.setBounds(40, 60, 200, 40)
    val connectButton = JButton("connect")
    connectButton.setBounds(40, 110, 200, 40)

    //hubPanel
    val roomIdTextField = JTextField("Room Id")
    roomIdTextField.setBounds(40, 10, 200, 40)
    val connectToRoomButton = JButton("Connect to room")
    connectToRoomButton.setBounds(40, 60, 200, 40)
    val createRoomButton = JButton("Create Room")
    createRoomButton.setBounds(40, 110, 200, 40)

    //Room panel
    val nrQuetionLabel = JLabel("Question 1",SwingConstants.CENTER)
    nrQuetionLabel.setBounds(50,0,300,50)
    nrQuetionLabel.font = Font("Serif", Font.BOLD, 25)

    val questionLabel = JLabel("<html>Pytanie na śniadanie XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDddd<html>",SwingConstants.CENTER)
    questionLabel.setBounds(50,40,300,150)
    questionLabel.font = Font("Serif", Font.BOLD, 25)

    val answerGroup = JPanel(GridLayout(2,2,10,10))
    answerGroup.setBounds(45,250,300,300)
    answerGroup.background = Color.BLACK

    val answer1 = JButton("A")
    answer1.background = Color.RED
    answer1.setSize(Dimension(75, 75))
    answer1.font = Font("Serif", Font.BOLD, 50)

    val answer2 = JButton("B")
    answer2.background = Color.GREEN
    answer2.setSize(Dimension(75, 75))
    answer2.font = Font("Serif", Font.BOLD, 50)

    val answer3 = JButton("C")
    answer3.background = Color.YELLOW
    answer3.setSize(Dimension(75, 75))
    answer3.font = Font("Serif", Font.BOLD, 50)

    val answer4 = JButton("D")
    answer4.background = Color.BLUE
    answer4.setSize(Dimension(75, 75))
    answer4.font = Font("Serif", Font.BOLD, 50)


    val frame = JFrame("Hello, Kotlin/Swing")
    frame.layout = null

    val crd = CardLayout()
    val mainPanel = JPanel(crd)



    //Connection Panel
    val connectionPanel = JPanel(null)
    connectionPanel.background = Color.RED
    connectionPanel.add(adresTextField, BorderLayout.CENTER)
    connectionPanel.add(portTextField, BorderLayout.CENTER)
    connectionPanel.add(connectButton, BorderLayout.CENTER)



    //Hub Panel
    val hubPanel = JPanel(null)
    hubPanel.background = Color.BLUE
    hubPanel.add(roomIdTextField)
    hubPanel.add(connectToRoomButton)
    hubPanel.add(createRoomButton)

    //Room Panel
    val roomPanel = JPanel(null)
    roomPanel.background = Color.MAGENTA

    roomPanel.add(nrQuetionLabel)
    roomPanel.add(questionLabel)
    roomPanel.add(answerGroup)
    answerGroup.add(answer1)
    answerGroup.add(answer2)
    answerGroup.add(answer3)
    answerGroup.add(answer4)

    frame.setContentPane(connectionPanel)

    connectButton.addActionListener {
        connectToServer(adresTextField.text, portTextField.text)
        frame.contentPane.remove(connectionPanel)
        frame.repaint()
        frame.setContentPane(hubPanel)
        frame.invalidate()
        frame.validate()
        frame.repaint()

//        crd.next(mainPanel)
    }

    connectToRoomButton.addActionListener({
        frame.contentPane.remove(hubPanel)
        frame.repaint()

        frame.setContentPane(roomPanel)
        frame.invalidate()
        frame.validate()
        frame.repaint()
    })

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(Dimension(400, 600))
    frame.setLocationRelativeTo(null)


    frame.setVisible(true)
}

fun main(args: Array<String>) {

    connectScreen()



}