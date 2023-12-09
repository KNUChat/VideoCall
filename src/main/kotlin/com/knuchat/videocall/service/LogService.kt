package com.knuchat.videocall.service

import com.knuchat.videocall.dto.LogDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class LogService(
    private val logDtoKafkaTemplate: KafkaTemplate<String, LogDto>
) {
    fun send(logDto: LogDto) {
        logDtoKafkaTemplate.send("log", logDto)
    }
}