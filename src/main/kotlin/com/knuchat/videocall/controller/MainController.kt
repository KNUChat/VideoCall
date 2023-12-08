package com.knuchat.videocall.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/hello")
    fun hello(): String {
        logger.info { "Said hello" }
        return "hello"
    }
}