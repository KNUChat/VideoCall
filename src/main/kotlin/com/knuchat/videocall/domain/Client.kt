package com.knuchat.videocall.domain

import org.springframework.web.socket.WebSocketSession

class Client(
    val user: User,
    private val session: WebSocketSession
) {
    val id get() = session.id

    fun send(message: SocketMessage) {
        session.send(message)
    }
}


fun WebSocketSession.send(message: SocketMessage) {
    this.sendMessage(message.serialize())
}
