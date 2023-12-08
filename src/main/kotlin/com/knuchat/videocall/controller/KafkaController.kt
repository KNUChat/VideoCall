package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.RoomDto
import com.knuchat.videocall.enums.RoomStatus
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaController(
    private val roomDtoKafkaTemplate: KafkaTemplate<String, RoomDto>
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @MessageMapping("/connect")
    fun connect(@Payload roomId: String) {
        logger.info("Connected at Room {}", roomId)

        val roomDto = RoomDto(roomId, RoomStatus.CONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload roomId: String) {
        logger.info("Disconnected at Room {}", roomId)

        val roomDto = RoomDto(roomId, RoomStatus.DISCONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }
}
