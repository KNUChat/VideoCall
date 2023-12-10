package com.knuchat.videocall.controller

import com.knuchat.videocall.dto.ConnectDto
import com.knuchat.videocall.dto.DisconnectDto
import com.knuchat.videocall.service.LogService
import com.knuchat.videocall.service.NoticeService
import com.knuchat.videocall.utils.Logger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    logService: LogService,
    private val noticeService: NoticeService
) {
    private val logger = Logger(KotlinLogging.logger {}, logService)

    @GetMapping("/test/hello")
    fun hello(): String {
        logger.info("Test hello")

        return "hello"
    }

    @GetMapping("/test/connect")
    fun connect(connectDto: ConnectDto) {
        logger.info("Test to connected", connectDto.roomId, connectDto.senderId)

        noticeService.connect(connectDto)
    }

    @GetMapping("/test/disconnect")
    fun disconnect(disconnectDto: DisconnectDto) {
        logger.info("Test to disconnected", disconnectDto.roomId, disconnectDto.senderId)

        noticeService.disconnect(disconnectDto)
    }
}