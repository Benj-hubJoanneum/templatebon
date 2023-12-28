package com.ssi.schaefer.lunchbon.businesslogic

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Component

@Component
class FirebaseAuthentication {

    fun verifyFirebaseToken(idToken: String): FirebaseToken? {
        val auth = FirebaseAuth.getInstance()
        return try {
            val decodedToken = auth.verifyIdToken(idToken)
            decodedToken
        } catch (e: Exception) {
            null
        }
    }
}