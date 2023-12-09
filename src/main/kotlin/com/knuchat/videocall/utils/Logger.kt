package com.knuchat.videocall.utils

import com.knuchat.videocall.dto.LogDto
import com.knuchat.videocall.enums.LogType
import com.knuchat.videocall.service.LogService
import io.github.oshai.kotlinlogging.KLogger


class Logger(
    private val logger: KLogger,
    private val logService: LogService
) {
    fun info(message: String, roomId: String? = null) {
        logger.info { "$message at Room $roomId" }

        val logDto = LogDto(LogType.INFO, message, roomId)
        logService.send(logDto)
    }

    fun error(message: String, roomId: String? = null) {
        logger.error { "$message at Room $roomId" }

        val logDto = LogDto(LogType.ERROR, message, roomId)
        logService.send(logDto)
    }
}
