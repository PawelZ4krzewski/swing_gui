import java.awt.Color
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class HubScreen(onNavigateCreateRoom: () -> Unit, onNavigateConnectToRoom: () -> Unit) : JPanel() {
    init{

        val roomIdTextField = JTextField("Room Id")
        roomIdTextField.setBounds(40, 10, 200, 40)
        val connectToRoomButton = JButton("Connect to room")
        connectToRoomButton.setBounds(40, 60, 200, 40)
        val createRoomButton = JButton("Create Room")
        createRoomButton.setBounds(40, 110, 200, 40)

        //Hub Panel
        layout = null
        background = Color.BLUE
        add(roomIdTextField)
        add(connectToRoomButton)
        add(createRoomButton)


        createRoomButton.addActionListener {
            onNavigateCreateRoom()
        }

        connectToRoomButton.addActionListener {
            onNavigateConnectToRoom()
        }

    }
}