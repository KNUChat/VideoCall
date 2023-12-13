package com.knuchat.videocall.dto

data class DisconnectDto(
    val roomId: String,
    val senderId: String,
    val receiverId: String
)
