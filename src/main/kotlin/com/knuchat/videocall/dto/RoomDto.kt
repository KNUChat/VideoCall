package com.knuchat.videocall.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RoomDto(
    @JsonProperty("id") val id: Int
)
