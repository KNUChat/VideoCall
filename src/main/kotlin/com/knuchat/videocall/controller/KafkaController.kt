package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.RoomDto
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
    fun connect(@Payload roomId: Int) {
        logger.info("Connected at Room {}", roomId)

        val roomDto = RoomDto(roomId)
        roomDtoKafkaTemplate.send("connect-video-call-room", roomDto)
    }
}
