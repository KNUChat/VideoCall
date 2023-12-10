package com.knuchat.videocall.service

import com.knuchat.videocall.dto.RoomConnectDto
import com.knuchat.videocall.dto.RoomDisconnectDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class RoomService(
    private val roomConnectDtoKafkaTemplate: KafkaTemplate<String, RoomConnectDto>,
    private val roomDisconnectDtoKafkaTemplate: KafkaTemplate<String, RoomDisconnectDto>
) {
    fun connect(roomConnectDto: RoomConnectDto) {
        roomConnectDtoKafkaTemplate.send("connect-video-call", roomConnectDto)
    }

    fun disconnect(roomDisconnectDto: RoomDisconnectDto) {
        roomDisconnectDtoKafkaTemplate.send("disconnect-video-call", roomDisconnectDto)
    }
}