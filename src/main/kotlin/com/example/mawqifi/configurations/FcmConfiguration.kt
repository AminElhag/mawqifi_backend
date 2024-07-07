package com.example.mawqifi.configurations

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException


@Configuration
class FcmConfiguration {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        try {
            val googleCredentials =
                GoogleCredentials.fromStream(ClassPathResource("mawqifi_firebase.json").inputStream)
            val firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build()
            val app = FirebaseApp.initializeApp(firebaseOptions)
            return FirebaseMessaging.getInstance(app)
        } catch (e: IOException) {
            throw IOException(e)
        }

    }
}