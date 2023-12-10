package com.knuchat.videocall.service

import com.knuchat.videocall.dto.ConnectDto
import com.knuchat.videocall.dto.DisconnectDto
import com.knuchat.videocall.dto.VideoCallDto
import com.knuchat.videocall.enums.VideoCallStatus
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class RoomService(
    private val videoCallDtoKafkaTemplate: KafkaTemplate<String, VideoCallDto>
) {
    fun connect(connectDto: ConnectDto) {

        val videoCallDto = VideoCallDto(
            roomId = connectDto.roomId.toLong(),
            senderId = connectDto.senderId.toLong(),
            receiverId = connectDto.receiverId.toLong(),
            videoCallStatus = VideoCallStatus.CONNECTED
        )
        videoCallDtoKafkaTemplate.send("connect-video-call-room", videoCallDto)
    }

    fun disconnect(disconnectDto: DisconnectDto) {

        val videoCallDto = VideoCallDto(
            roomId = disconnectDto.roomId.toLong(),
            senderId = disconnectDto.senderId.toLong(),
            receiverId = disconnectDto.receiverId.toLong(),
            videoCallStatus = VideoCallStatus.DISCONNECTED
        )
        videoCallDtoKafkaTemplate.send("connect-video-call-room", videoCallDto)
    }
}