package com.knuchat.videocall.service

import com.knuchat.videocall.dto.RoomDto
import com.knuchat.videocall.enums.RoomStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class RoomService(
    private val roomDtoKafkaTemplate: KafkaTemplate<String, RoomDto>
) {
    fun connectToRoom(roomId: String) {
        val roomDto = RoomDto(roomId, RoomStatus.CONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }

    fun disconnectToRoom(roomId: String) {
        val roomDto = RoomDto(roomId, RoomStatus.DISCONNECTED)
        roomDtoKafkaTemplate.send("video-call-room", roomDto)
    }
}