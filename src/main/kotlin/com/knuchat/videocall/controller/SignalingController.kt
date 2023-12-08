package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.WebSocketMessage
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class SignalingController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @MessageMapping("/peer/offer/{key}/{roomId}")
    @SendTo("/topic/peer/offer/{key}/{roomId}")
    fun handleOffer(
        @Payload message: WebSocketMessage,
        @DestinationVariable(value = "key") receiverKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): WebSocketMessage {
        val senderKey = message.key
        logger.info("Handled offer from {} to {} at Room {}",
            senderKey, receiverKey, roomId)
        return message
    }

    @MessageMapping("/peer/iceCandidate/{key}/{roomId}")
    @SendTo("/topic/peer/iceCandidate/{key}/{roomId}")
    fun handleIceCandidate(
        @Payload message: WebSocketMessage,
        @DestinationVariable(value = "key") receiverKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): WebSocketMessage {
        val senderKey = message.key
        logger.info("Handled ICE candidate from {} to {} at Room {}",
            senderKey, receiverKey, roomId)
        return message
    }

    @MessageMapping("/peer/answer/{key}/{roomId}")
    @SendTo("/topic/peer/answer/{key}/{roomId}")
    fun handleAnswer(
        @Payload message: WebSocketMessage,
        @DestinationVariable(value = "key") receiverKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): WebSocketMessage {
        val senderKey = message.key
        logger.info("Handled answer from {} to {} at Room {}",
            senderKey, receiverKey, roomId)
        return message
    }

    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    fun callKeys(@Payload unknown: Any): Any {
        logger.info("Called keys")
        return unknown
    }

    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    fun sendKey(@Payload key: String): String {
        logger.info("Sent key: {}", key)
        return key
    }
}
