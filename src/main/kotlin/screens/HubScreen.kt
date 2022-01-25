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
        val nameTextField = JTextField("Nickname")
        nameTextField.setBounds(40, 60, 200, 40)
        val connectToRoomButton = JButton("Connect to room")
        connectToRoomButton.setBounds(40, 110, 200, 40)
        val createRoomButton = JButton("Create Room")
        createRoomButton.setBounds(40, 200, 200, 40)

        //Hub Panel
        layout = null
        background = Color.BLUE
        add(roomIdTextField)
        add(nameTextField)
        add(connectToRoomButton)
        add(createRoomButton)

        connectToRoomButton.addActionListener {
            if (nameTextField.text.length <= 1) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Twój nick jest za krótki!"
                )
                return@addActionListener
            }
            service.service.joinRoom(roomIdTextField.text, nameTextField.text)
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                println("Start game screen $it")
                when (it) {
                    is Message.Joined -> {
                        frame.navigateTo(WaitGameScreen(frame))
                        coroutineScope.cancel()
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
        createRoomButton.addActionListener {
            frame.navigateTo(CreateRoomScreen(frame))
            coroutineScope.cancel()
        }
    }
}