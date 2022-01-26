package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import service.Message
import java.awt.Font
import javax.swing.*

class RankingScreen(frame: JFrame) : JPanel() {

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

        val endGame = JButton("Zakoncz").apply{
            setBounds(100, 500, 200, 50)

        }

        layout = null
        add(roomIdSQLabel)
        add(questionShowJTextField)
        add(endGame)

        endGame.addActionListener {
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.messages.collect {
                println("Ranking screen $it")
                when (it) {
                    is Message.Ranking -> {
                        JLabel("#$i. $it").apply{
                            setBounds(20, (100+25*i++), 350, 50)
                            font = Font("Serif", Font.BOLD, 25)
                            this@RankingScreen.add(this)
                            i++
                        }
                    }
                }
            }
        }
    }

}