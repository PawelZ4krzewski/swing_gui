import java.lang.Exception

sealed class Message {
    class Error(val exception: Exception, val message: String): Message()
    object Connected : Message()
}