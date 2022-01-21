import java.awt.BorderLayout
import java.awt.Color
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField

class ConnectScreen(onNavigate: () -> Unit) : JPanel() {
    init {
        //connectionPanel
        val adresTextField = JTextField("Adres")
        adresTextField.setBounds(40, 10, 200, 40)
        val portTextField = JTextField("Port")
        portTextField.setBounds(40, 60, 200, 40)
        val connectButton = JButton("connect")
        connectButton.setBounds(40, 110, 200, 40)

        //Connection Panel
        background = Color.RED
        add(adresTextField, BorderLayout.CENTER)
        add(portTextField, BorderLayout.CENTER)
        add(connectButton, BorderLayout.CENTER)

        connectButton.addActionListener {
            connectToServer(adresTextField.text, portTextField.text)
            onNavigate()
        }

        repaintScreen()
    }
}