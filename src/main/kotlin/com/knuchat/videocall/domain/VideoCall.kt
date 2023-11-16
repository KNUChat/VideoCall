package com.knuchat.videocall.domain

class VideoCall(val chatRoom: ChatRoom) {

    private val clients = mutableMapOf<String, Client>()

    val allClients = clients.values.toList()

    fun addClient(client: Client) {
        clients[client.id] = client
    }

    fun removeClientById(id: String) {
        clients.remove(id)
    }
}
