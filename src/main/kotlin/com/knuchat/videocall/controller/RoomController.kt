package com.knuchat.videocall.controller

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
    fun connect(@Payload roomId: String) {
        logger.info("Connected", roomId)

        roomService.connectToRoom(roomId)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload roomId: String) {
        logger.info("Disconnected", roomId)

        roomService.disconnectToRoom(roomId)
    }
}
