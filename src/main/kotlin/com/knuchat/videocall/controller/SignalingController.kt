package com.knuchat.videocall.controller

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController
class SignalingController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @MessageMapping("/peer/offer/{camKey}/{roomId}")
    @SendTo("/topic/peer/offer/{camKey}/{roomId}")
    fun handleOffer(
        @Payload offer: String,
        @DestinationVariable(value = "camKey") camKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): String {
        logger.info("Handled offer from {} at Room {}: {}", camKey, roomId, offer)
        return offer
    }

    @MessageMapping("/peer/iceCandidate/{camKey}/{roomId}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{roomId}")
    fun handleIceCandidate(
        @Payload candidate: String,
        @DestinationVariable(value = "camKey") camKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): String {
        logger.info("Handled ICE candidate from {} at Room {}: {}", camKey, roomId, candidate)
        return candidate
    }

    @MessageMapping("/peer/answer/{camKey}/{roomId}")
    @SendTo("/topic/peer/answer/{camKey}/{roomId}")
    fun handleAnswer(
        @Payload answer: String,
        @DestinationVariable(value = "camKey") camKey: String,
        @DestinationVariable(value = "roomId") roomId: String
    ): String {
        logger.info("Handled answer from {} at Room {}: {}", camKey, roomId, answer)
        return answer
    }

    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    fun callKey(@Payload message: String): String {
        logger.info("Called key: {}", message)
        return message
    }

    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    fun sendKey(@Payload message: String): String {
        logger.info("Sent key: {}", message)
        return message
    }
}
