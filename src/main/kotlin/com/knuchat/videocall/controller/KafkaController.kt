package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.RoomDto
import com.knuchat.videocall.enums.RoomStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.RestController
import io.github.oshai.kotlinlogging.KotlinLogging

@RestController
class KafkaController(
    private val roomDtoKafkaTemplate: KafkaTemplate<String, RoomDto>
) {
    private val logger = KotlinLogging.logger {}

    @MessageMapping("/connect")
    fun connect(@Payload roomId: String) {
        logger.info { "Connected at Room $roomId" }

        val roomDto = RoomDto(roomId, RoomStatus.CONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload roomId: String) {
        logger.info { "Disconnected at Room $roomId" }

        val roomDto = RoomDto(roomId, RoomStatus.DISCONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }
}
