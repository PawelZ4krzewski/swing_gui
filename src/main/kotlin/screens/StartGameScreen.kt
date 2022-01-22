package screens

import java.awt.Color
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class StartGameScreen(onNavigate: () -> Unit): JPanel() {
    init{

        //startGamePanel
        val roomIdTextField = JLabel("Room Id")
        roomIdTextField.setBounds(200, 10, 200, 40)
        val startGameButton = JButton("START")
        startGameButton.setBounds(100, 110, 200, 40)

        //Hub Panel
        layout = null
        background = Color.PINK
        add(roomIdTextField)
        add(startGameButton)


        startGameButton.addActionListener {
            onNavigate()
        }
    }
}