package com.knuchat.videocall.dto

import com.knuchat.videocall.enums.RoomStatus

data class RoomDto(
    val id: String,
    val status: RoomStatus
)
