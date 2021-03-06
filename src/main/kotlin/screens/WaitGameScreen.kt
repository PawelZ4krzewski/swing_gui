package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navigateTo
import service.Message
import java.awt.Color
import javax.swing.*

class WaitGameScreen(frame: JFrame, hash: String) : JPanel() {
    init {

        //startGamePanel
        val roomIdTextField = JLabel(hash)
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
                    is Message.HostDisconnected -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>Host has disconnected<html>"
                        )
                        frame.navigateTo(HubScreen(frame))
                        coroutineScope.cancel()
                    }
                    is Message.Disconnected -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>You've been disconnected<html>"
                        )
                        frame.navigateTo(ConnectScreen(frame))
                        coroutineScope.cancel()
                    }
                }
            }
        }
    }
}