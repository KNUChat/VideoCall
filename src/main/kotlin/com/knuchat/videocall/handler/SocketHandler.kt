package com.knuchat.videocall.handler

import com.knuchat.videocall.domain.*
import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class SocketHandler(
    private val videoCallRegistry: VideoCallRegistry
) : TextWebSocketHandler() {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        TODO()
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        TODO()
    }

    override fun handleTextMessage(session: WebSocketSession, serializedMessage: TextMessage) {
        val message = serializedMessage.deserialize()
        when (message.type) {
            SocketMessage.Type.TEXT ->
                TODO()
            SocketMessage.Type.OFFER ->
                handleSignal(session, message, User(message.from))
            SocketMessage.Type.ANSWER ->
                handleSignal(session, message, User(message.from))
            SocketMessage.Type.ICE ->
                handleSignal(session, message, User(message.from))
            SocketMessage.Type.JOIN ->
                handleToJoin(session, User(message.from), ChatRoom(message.data))
            SocketMessage.Type.LEAVE ->
                handleToLeave(session, User(message.from))
        }
    }

    private fun handleSignal(session: WebSocketSession, message: SocketMessage, user: User) {
        TODO()
    }

    private fun handleToJoin(session: WebSocketSession, user: User, chatRoom: ChatRoom) {
        TODO()
    }

    private fun handleToLeave(session: WebSocketSession, user: User) {
        TODO()
    }
}
