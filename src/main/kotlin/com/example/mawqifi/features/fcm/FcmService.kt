package com.example.mawqifi.features.fcm

import com.example.mawqifi.features.parking.service.dto.ParkingDto
import com.google.firebase.messaging.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Service
class FcmService {

    @Autowired
    lateinit var firebaseMessaging: FirebaseMessaging

    fun sendNotificationToSpecificDevice(note: MessageDTO, token: String?): String {
        try {
            val notification: Notification = Notification
                .builder()
                .setTitle(note.subject)
                .setBody(note.content)
                .setImage(note.image)
                .build()
            val message: Message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(note.data)
                .build()
            return firebaseMessaging.send(message)
        } catch (e: FirebaseMessagingException) {
            throw e
        }

    }

    fun sendNotificationToSpecificDevice(parkingDto: ParkingDto, token: String?): String {
        try {
            val notification: Notification = Notification
                .builder()
                .setTitle(parkingDto.name)
                .setBody(parkingDto.shortAddress)
                .setImage(parkingDto.bigImageUrl)
                .build()
            val message: Message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(parkingDto.toMap())
                .build()
            return firebaseMessaging.send(message)
        } catch (e: FirebaseMessagingException) {
            throw e
        }

    }

    fun sendNotificationToMultipleDevices(note: MessageDTO, tokens: List<String?>?): BatchResponse {
        try {
            val notification = Notification
                .builder()
                .setTitle(note.subject)
                .setBody(note.content)
                .setImage(note.image)
                .build()

            val message = MulticastMessage
                .builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .putAllData(note.data)
                .build()
            return firebaseMessaging.sendMulticast(message)
        } catch (e: FirebaseMessagingException) {
            throw e
        }

    }

    fun subscribeToTopic(tokens: List<String?>?, topic: String?) {
        firebaseMessaging.subscribeToTopic(tokens, topic)
    }

    fun unSubscribeToTopic(tokens: List<String?>?, topic: String?) {
        firebaseMessaging.unsubscribeFromTopic(tokens, topic)
    }

    fun sendNotificationToTopic(note: MessageDTO, topic: String?): String {
        val notification = Notification
            .builder()
            .setTitle(note.subject)
            .setBody(note.content)
            .setImage(note.image)
            .build()
        val message = Message
            .builder()
            .setTopic(topic)
            .setNotification(notification)
            .putAllData(note.data)
            .build()
        return firebaseMessaging.send(message)
    }
}


@RestController
class FcmController {
    @Autowired
    var fcmService: FcmService? = null

    @PostMapping("/single-notification")
    fun sendToSpecificDevice(
        @RequestBody note: MessageDTO,
        @RequestParam token: String
    ): String {
        return fcmService!!.sendNotificationToSpecificDevice(note, token)
    }
}

data class MessageDTO(
    val subject: String,
    val content: String,
    val image: String,
    val data: Map<String, String>,
)