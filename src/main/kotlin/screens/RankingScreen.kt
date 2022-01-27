package screens

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import navigateTo
import repaintScreen
import service.Message
import java.awt.Font
import javax.swing.*

class RankingScreen(frame: JFrame) : JPanel() {
    init {
        val questionShowJTextField = JLabel("RANKING").apply {
            setBounds(20, 75, 350, 50)
            font = Font("Serif", Font.BOLD, 40)
        }

        val endGame = JButton("Zakoncz").apply {
            setBounds(100, 500, 200, 50)
        }

        layout = null
        add(questionShowJTextField)
        add(endGame)

        endGame.addActionListener {
            frame.navigateTo(HubScreen(frame))
        }

        var playersTexts = listOf<JLabel>()

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            service.service.ranking.collect {
                println("Ranking screen $it")
                playersTexts.forEach(this@RankingScreen::remove)
                playersTexts = listOf()
                it.forEachIndexed { i, s ->
                    playersTexts = playersTexts + JLabel("#$i. $s").apply {
                        setBounds(20, (100 + 25 * i), 350, 50)
                        font = Font("Serif", Font.BOLD, 25)
                        this@RankingScreen.add(this)
                    }
                }
                repaintScreen()
            }
        }
    }

}