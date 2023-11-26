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

    @MessageMapping("/peer/offer/{camKey}/{chatRoomId}")
    @SendTo("/topic/peer/offer/{camKey}/{chatRoomId}")
    fun handleOffer(
        @Payload offer: String,
        @DestinationVariable(value = "chatRoomId") chatRoomId: String,
        @DestinationVariable(value = "camKey") camKey: String
    ): String {
        logger.info("Handled offer at Chat Room {}: {}", chatRoomId, offer)
        return offer
    }

    @MessageMapping("/peer/iceCandidate/{camKey}/{chatRoomId}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{chatRoomId}")
    fun handleIceCandidate(
        @Payload candidate: String,
        @DestinationVariable(value = "chatRoomId") chatRoomId: String,
        @DestinationVariable(value = "camKey") camKey: String
    ): String {
        logger.info("Handled ICE candidate at Chat Room {}: {}", chatRoomId, candidate)
        return candidate
    }

    @MessageMapping("/peer/answer/{camKey}/{chatRoomId}")
    @SendTo("/topic/peer/answer/{camKey}/{chatRoomId}")
    fun handleAnswer(
        @Payload answer: String,
        @DestinationVariable(value = "chatRoomId") chatRoomId: String,
        @DestinationVariable(value = "camKey") camKey: String
    ): String {
        logger.info("Handled answer at Chat Room {}: {}", chatRoomId, answer)
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
