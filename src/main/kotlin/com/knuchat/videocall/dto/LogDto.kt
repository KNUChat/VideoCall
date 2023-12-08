package com.knuchat.videocall.dto

import com.knuchat.videocall.enums.LogType
import java.time.LocalDateTime

data class LogDto(
    val type: LogType,
    val message: String,
    val roomId: String?
) {
    val id: String? = null
    val userId: String? = null
    val service = "VideoCall"
    val time = LocalDateTime.now()
}
