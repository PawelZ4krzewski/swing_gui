package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import service.Message
import java.awt.Color
import javax.swing.*

class HubScreen(frame: JFrame) : JPanel() {
    init {

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
            frame.navigateTo(CreateRoomScreen(frame))
        }

        connectToRoomButton.addActionListener {
            service.service.joinRoom(roomIdTextField.text, "NICK")
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                println("Start game screen $it")
                when (it) {
                    is Message.Joined -> {
                        frame.navigateTo(WaitGameScreen(frame))
                    }
                    is Message.Error -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>Error message: ${it.exception.localizedMessage}<html>"
                        )
                    }
                }
            }
        }
    }
}