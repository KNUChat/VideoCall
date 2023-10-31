package com.knuchat.videocall

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VideoCallApplication

fun main(args: Array<String>) {
    runApplication<VideoCallApplication>(*args)
}
