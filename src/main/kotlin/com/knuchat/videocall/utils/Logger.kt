package com.knuchat.videocall.utils

import com.knuchat.videocall.dto.LogDto
import com.knuchat.videocall.enums.LogType
import com.knuchat.videocall.service.LogService
import io.github.oshai.kotlinlogging.KLogger


class Logger(
    private val logger: KLogger,
    private val logService: LogService
) {
    fun info(message: String, roomId: String? = null, userId: String? = null) {
        logger.info {
            message
                .plus(if (userId != null) " by User $userId" else "")
                .plus(if (roomId != null) " at Room $roomId" else "")
        }
        val logDto = LogDto(LogType.INFO, message, roomId, userId)
        logService.send(logDto)
    }

    fun error(message: String, roomId: String? = null, userId: String? = null) {
        logger.error {
            message
                .plus(if (userId != null) " by User $userId" else "")
                .plus(if (roomId != null) " at Room $roomId" else "")
        }
        val logDto = LogDto(LogType.ERROR, message, roomId, userId)
        logService.send(logDto)
    }
}
