package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.RoomConnectDto
import com.knuchat.videocall.dto.RoomDisconnectDto
import com.knuchat.videocall.service.LogService
import com.knuchat.videocall.service.RoomService
import com.knuchat.videocall.utils.Logger
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.RestController
import io.github.oshai.kotlinlogging.KotlinLogging

@RestController
class RoomController(
    logService: LogService,
    private val roomService: RoomService
) {
    private val logger = Logger(KotlinLogging.logger { }, logService)

    @MessageMapping("/connect")
    fun connect(@Payload roomConnectDto: RoomConnectDto) {
        logger.info("Connected", roomConnectDto.roomId)

        roomService.connect(roomConnectDto)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload roomDisconnectDto: RoomDisconnectDto) {
        logger.info("Disconnected", roomDisconnectDto.roomId)

        roomService.disconnect(roomDisconnectDto)
    }
}
