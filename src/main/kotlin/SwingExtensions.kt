import javax.swing.JFrame
import javax.swing.JPanel

fun JFrame.navigateTo(panel: JPanel) {
    contentPane.removeAll()
    contentPane = panel
    repaintScreen()
}