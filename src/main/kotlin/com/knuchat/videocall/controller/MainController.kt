package com.knuchat.videocall.controller

import com.knuchat.videocall.service.LogService
import com.knuchat.videocall.utils.Logger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
    logService: LogService
) {
    private val logger = Logger(KotlinLogging.logger {}, logService)

    @GetMapping("/hello")
    fun hello(): String {
        logger.info("Said hello")
        return "hello"
    }
}