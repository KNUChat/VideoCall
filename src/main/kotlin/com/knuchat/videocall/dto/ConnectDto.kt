package com.knuchat.videocall.dto

data class ConnectDto(
    val roomId: String,
    val senderId: String,
    val receiverId: String
)
