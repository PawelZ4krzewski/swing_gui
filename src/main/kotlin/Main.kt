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

    //createRoomPanel

    val roomIdLabel = JLabel("IDROOM",SwingConstants.CENTER)
    roomIdLabel.setBounds(50,0,300,50)
    roomIdLabel.font = Font("Serif", Font.BOLD, 25)

    val questionJTextField = JTextField("Question")
    questionJTextField.setBounds(20,75,350,50)

    val answer1JTextField = JTextField("Answer 1")
    answer1JTextField.setBounds(20,125,350,50)

    val answer2JTextField = JTextField("Answer 2")
    answer2JTextField.setBounds(20,175,350,50)

    val answer3JTextField = JTextField("Answer 3")
    answer3JTextField.setBounds(20,225,350,50)

    val answer4JTextField = JTextField("Answer 4")
    answer4JTextField.setBounds(20,275,350,50)

    val addQuestionButton = JButton("Add Question")
    addQuestionButton.setBounds(100, 400,200,50)

    val endCreateRoom = JButton("Zakoncz")
    endCreateRoom.setBounds(100, 500,200,50)

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
    answerGroup.add(answer1JButton)
    answerGroup.add(answer2JButton)
    answerGroup.add(answer3JButton)
    answerGroup.add(answer4JButton)

    //Create_Room Panel
    val createRoomPanel = JPanel(null)
    createRoomPanel.background = Color.YELLOW

    createRoomPanel.add(roomIdLabel)
    createRoomPanel.add(questionJTextField)
    createRoomPanel.add(answer1JTextField)
    createRoomPanel.add(answer2JTextField)
    createRoomPanel.add(answer3JTextField)
    createRoomPanel.add(answer4JTextField)
    createRoomPanel.add(addQuestionButton)
    createRoomPanel.add(endCreateRoom)



    frame.setContentPane(createRoomPanel)

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

    createRoomButton.addActionListener({
        frame.contentPane.remove(hubPanel)
        frame.repaint()
        frame.setContentPane(createRoomPanel)
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