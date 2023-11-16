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
        try {
            val videoCall = videoCallRegistry.findByClientId(session.id)!!

            videoCall.allClients.filter { it.user == user }.forEach {
                it.send(message.copy())
            }
            logger.debug("{} has sent {}", user, message.candidate ?: message.sdp)
        } catch (e: KotlinNullPointerException) {
            TODO()
        }
    }

    private fun handleToJoin(session: WebSocketSession, user: User, chatRoom: ChatRoom) {
        try {
            val client = Client(user, session)
            val videoCall = videoCallRegistry.findByChatRoom(chatRoom)!!

            videoCall.addClient(client)
            logger.debug("{} has joined video-call on {}", user, chatRoom)
        } catch (e: KotlinNullPointerException) {
            TODO()
        }
    }

    private fun handleToLeave(session: WebSocketSession, user: User) {
        try {
            val videoCall = videoCallRegistry.findByClientId(session.id)!!

            videoCall.removeClientById(session.id)
            logger.debug("{} will leave video-call on {}", user, videoCall.chatRoom)
        } catch (e: KotlinNullPointerException) {
            TODO()
        }
    }
}
