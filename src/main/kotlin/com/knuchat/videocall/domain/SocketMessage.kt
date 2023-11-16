package com.knuchat.videocall.domain

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.socket.TextMessage

data class SocketMessage(
    val from: String,
    val type: Type,
    val data: String,
    val candidate: Any?,
    val sdp: Any?
) {
    enum class Type(s: String) {
        TEXT("text"),
        OFFER("offer"),
        ANSWER("answer"),
        ICE("ice"),
        JOIN("join"),
        LEAVE("leave")
    }

    companion object {
        private val objectMapper = ObjectMapper()

        fun TextMessage.deserialize(): SocketMessage {
            return objectMapper.readValue(this.payload, SocketMessage::class.java)
        }

        fun SocketMessage.serialize(): TextMessage {
            val json = objectMapper.writeValueAsString(this)
            return TextMessage(json)
        }
    }
}
