import screens.*
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

fun main(args: Array<String>) {

    startGUI()
//    frame.contentPane = ConnectScreen(frame)
    val ranking = RoomScreen(frame, 2)
    frame.contentPane = ranking

    repaintScreen()
}