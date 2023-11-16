package com.knuchat.videocall.domain

import org.springframework.stereotype.Component

@Component
class VideoCallRegistry {
    private val chatRoomToVideoCallMap = mutableMapOf<ChatRoom, VideoCall>()
    private val clientIdToVideoCallMap = mutableMapOf<String, VideoCall>()

    fun findByChatRoom(chatRoom: ChatRoom) = chatRoomToVideoCallMap[chatRoom]
    fun findByClientId(clientId: String) = clientIdToVideoCallMap[clientId]

    fun isNotEmpty() = chatRoomToVideoCallMap.isNotEmpty()

    fun add(videoCall: VideoCall) {
        chatRoomToVideoCallMap[videoCall.chatRoom] = videoCall
        videoCall.allClients.forEach {
            clientIdToVideoCallMap[it.id] = videoCall
        }
    }

    fun removeByChatRoom(chatRoom: ChatRoom): VideoCall? {
        return chatRoomToVideoCallMap.remove(chatRoom)?.also { videoCall ->
            videoCall.allClients.forEach {
                clientIdToVideoCallMap[it.id] = videoCall
            }
        }
    }

    fun removeByClientId(clientId: String): VideoCall? {
        return clientIdToVideoCallMap.remove(clientId)?.also { videoCall ->
            chatRoomToVideoCallMap[videoCall.chatRoom] = videoCall
        }
    }
}
