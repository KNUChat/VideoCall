package com.knuchat.videocall.config

import com.knuchat.videocall.dto.LogDto
import com.knuchat.videocall.dto.RoomDto
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@EnableKafka
class KafkaConfig {
    @Value("\${spring.kafka.producer.bootstrap-servers}")
    lateinit var bootstrapServer: String

    @Bean
    fun logDtoKafkaTemplate() = KafkaTemplate(logDtoProducerFactory())

    @Bean
    fun roomDtoKafkaTemplate() = KafkaTemplate(roomDtoProducerFactory())

    @Bean
    fun logDtoProducerFactory() = DefaultKafkaProducerFactory<String, LogDto>(
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
    )

    @Bean
    fun roomDtoProducerFactory() = DefaultKafkaProducerFactory<String, RoomDto>(
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
    )
}
