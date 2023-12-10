package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.ConnectDto
import com.knuchat.videocall.dto.DisconnectDto
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
    fun connect(@Payload connectDto: ConnectDto) {
        logger.info("Connected", connectDto.roomId, connectDto.senderId)

        roomService.connect(connectDto)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload disconnectDto: DisconnectDto) {
        logger.info("Disconnected", disconnectDto.roomId, disconnectDto.senderId)

        roomService.disconnect(disconnectDto)
    }
}
