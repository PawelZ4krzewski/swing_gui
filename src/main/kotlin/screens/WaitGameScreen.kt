package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import service.Message
import java.awt.Color
import javax.swing.*

class WaitGameScreen(frame: JFrame) : JPanel() {
    init {

        //startGamePanel
        val roomIdTextField = JLabel("Room Id")
        roomIdTextField.setBounds(200, 10, 200, 40)

        //Hub Panel
        layout = null
        background = Color.PINK
        add(roomIdTextField)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                when (it) {
                    is Message.Question -> {
                        frame.navigateTo(RoomScreen(frame, 0))
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
    }
}