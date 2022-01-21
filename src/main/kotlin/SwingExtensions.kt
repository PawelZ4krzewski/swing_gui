import javax.swing.JPanel
import javax.swing.JTextField

fun JPanel.addTextField(
    text: String,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    options: (JTextField.() -> Unit)? = null
) {
    val textField = JTextField(text).apply {
        setBounds(x, y, width, height)
        options?.invoke(this)
    }
    add(textField)
}