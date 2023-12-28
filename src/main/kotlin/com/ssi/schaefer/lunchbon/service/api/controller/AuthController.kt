package com.ssi.schaefer.lunchbon.service.api.controller

import com.ssi.schaefer.lunchbon.businesslogic.FirebaseAuthentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    lateinit var firebaseAuthentication: FirebaseAuthentication

    @PostMapping("/verifyToken")
    fun verifyToken(@RequestHeader("Authorization") authToken: String?): ResponseEntity<String> {
        if (authToken != null && authToken.startsWith("Bearer")) {
            val idToken = authToken.substring(7)
            val firebaseToken = firebaseAuthentication.verifyFirebaseToken(idToken)
            return if (firebaseToken != null) {
                val userId = firebaseToken.uid
                ResponseEntity.ok("Token verified. User ID: $userId")
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized")
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized")
        }
    }
}