package screens

import kotlinx.coroutines.*
import navigateTo
import repaintScreen
import service.Message
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*


class ConnectScreen(frame: JFrame) : JPanel() {
    init {
        //connectionPanel
        val adresTextField = JTextField("Adres")
        adresTextField.setBounds(40, 10, 200, 40)
        val portTextField = JTextField("Port")
        portTextField.addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent) {
                val c = e.keyChar
                if (!(c in '0'..'9' ||
                            c.code == KeyEvent.VK_BACK_SPACE ||
                            c.code == KeyEvent.VK_DELETE)
                ) {
                    toolkit.beep()
                    e.consume()
                }
            }
        })
        portTextField.setBounds(40, 60, 200, 40)
        val connectButton = JButton("connect")
        connectButton.setBounds(40, 110, 200, 40)

        //Connection Panel
        layout = null
        background = Color.RED
        add(adresTextField, BorderLayout.CENTER)
        add(portTextField, BorderLayout.CENTER)
        add(connectButton, BorderLayout.CENTER)

        connectButton.addActionListener {
            val port = portTextField.text.toIntOrNull()
            if (port != null ) {
                service.service.connectTo(adresTextField.text, portTextField.text.toInt())
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Wpisano błędny port"
                )
            }
        }

        repaintScreen()

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        val job = coroutineScope.launch {
            service.service.messages.collect {
                print(it)
                when (it) {
                    is Message.Connected -> {
                        frame.navigateTo(HubScreen(frame))
                        coroutineScope.cancel()
                    }
                    is Message.Error -> {
                        JOptionPane.showMessageDialog(
                            frame,
                            "<html>Could not connected! Error message: ${it.exception.localizedMessage}<html>"
                        )
                    }
                }
            }
        }

    }
}