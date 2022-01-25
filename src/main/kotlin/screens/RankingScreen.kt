package screens

import java.awt.Font
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class RankingScreen(onNavigate: () -> Unit) : JPanel() {

    val users = mutableListOf<String>()

    init{
        users.add("dupa")
        users.add("raz")
//        "dwa","trzy")
        val roomIdSQLabel = JLabel("IDROOM", SwingConstants.CENTER).apply{
            setBounds(50, 0, 300, 50)
            font = Font("Serif", Font.BOLD, 25)
        }

        val questionShowJTextField = JLabel("RANKING").apply{
            setBounds(20, 75, 350, 50)
            font = Font("Serif", Font.BOLD, 40)
        }


        var i = 1
        val usersJLabel = mutableListOf<JLabel>()
        users.take(5).forEach{
            usersJLabel.add(JLabel("$i. $it").apply{
                setBounds(20, (100+25*i++), 350, 50)
                font = Font("Serif", Font.BOLD, 25)
                this@RankingScreen.add(this)
            })


        }

        val endGame = JButton("Zakoncz").apply{
            setBounds(100, 500, 200, 50)

        }

        layout = null
        add(roomIdSQLabel)
        add(questionShowJTextField)
        add(endGame)

        endGame.addActionListener {
            onNavigate()
        }
    }

}