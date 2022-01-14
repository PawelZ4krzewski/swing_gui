import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Color
import java.awt.Dimension
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

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setSize(Dimension(300, 200))
    frame.setLocationRelativeTo(null)


    frame.setVisible(true)
}

fun main(args: Array<String>) {

    connectScreen()



}