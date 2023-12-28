package com.ssi.schaefer.lunchbon.acceslogic

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FireBaseConfig {

    @Bean
    fun firestore(): Firestore {
        val credentials = GoogleCredentials.fromStream(ClassPathResource("google-services.json").inputStream)
        val firestoreOptions = FirestoreOptions.newBuilder()
            .setCredentials(credentials)
            .build()

        return firestoreOptions.service
    }

    @Bean
    fun firebaseApp(): FirebaseApp {
        val resource = ClassPathResource("google-services.json")
        val credentials = GoogleCredentials.fromStream(resource.inputStream)
        val options = FirebaseOptions.Builder()
            .setCredentials(credentials)
            .build()

        return FirebaseApp.initializeApp(options)
    }
}