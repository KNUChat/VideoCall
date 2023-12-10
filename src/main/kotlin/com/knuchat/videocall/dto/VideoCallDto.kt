package com.knuchat.videocall.dto

import com.knuchat.videocall.enums.VideoCallStatus

data class VideoCallDto(
    val roomId: Long,
    val senderId: Long,
    val receiverId: Long,
    val videoCallStatus: VideoCallStatus
)
