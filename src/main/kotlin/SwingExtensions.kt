import javax.swing.JFrame
import javax.swing.JPanel

fun JFrame.navigateTo(panel: JPanel) {
    frame.contentPane.removeAll()
    frame.contentPane = panel
    repaintScreen()
}