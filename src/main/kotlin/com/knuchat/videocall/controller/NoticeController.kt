package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.ConnectDto
import com.knuchat.videocall.dto.DisconnectDto
import com.knuchat.videocall.service.LogService
import com.knuchat.videocall.service.NoticeService
import com.knuchat.videocall.utils.Logger
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.RestController
import io.github.oshai.kotlinlogging.KotlinLogging

@RestController
class NoticeController(
    logService: LogService,
    private val noticeService: NoticeService
) {
    private val logger = Logger(KotlinLogging.logger { }, logService)

    @MessageMapping("/connect")
    fun connect(@Payload connectDto: ConnectDto) {
        logger.info("Connected", connectDto.roomId, connectDto.senderId)

        noticeService.connect(connectDto)
    }

    @MessageMapping("/disconnect")
    fun disconnect(@Payload disconnectDto: DisconnectDto) {
        logger.info("Disconnected", disconnectDto.roomId, disconnectDto.senderId)

        noticeService.disconnect(disconnectDto)
    }
}
