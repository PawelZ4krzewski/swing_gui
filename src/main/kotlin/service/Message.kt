package service

import java.lang.Exception

sealed class Message {
    data class Error(val exception: Exception, val message: String): Message()
    object Connected : Message()
    data class Created(val hash: String) : Message()
}