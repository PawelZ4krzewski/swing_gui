import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import screens.*
import service.Message
import java.awt.*
import javax.swing.*

val frame = JFrame("Hello, Kotlin/Swing")

fun startGUI() {

    frame.layout = null
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.size = Dimension(400, 600)
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}

fun repaintScreen() {
    frame.invalidate()
    frame.validate()
    frame.repaint()
}

fun main() {

    startGUI()
    frame.contentPane = ConnectScreen(frame)
    repaintScreen()
}