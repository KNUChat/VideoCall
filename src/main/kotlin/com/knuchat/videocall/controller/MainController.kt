package com.knuchat.videocall.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/hello")
    fun hello(): String {
        logger.info("Said hello")
        return "hello"
    }
}