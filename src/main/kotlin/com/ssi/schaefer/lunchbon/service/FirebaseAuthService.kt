package com.ssi.schaefer.lunchbon.service

import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import com.ssi.schaefer.lunchbon.service.api.model.ResultEntity
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService  (
    private val helperMethods: HelperMethods,
)  {

    fun registerUser(email: String, password: String): ResultEntity {
        try {
            val userRecord = FirebaseAuth.getInstance().createUser(UserRecord.CreateRequest().setEmail(email).setPassword(password))
            return ResultEntity(true, "User registered successfully")
        } catch (e: FirebaseAuthException) {
            return ResultEntity(false, "Firebase Authentication error: ${e.errorCode}")
        }
    }

    fun authenticateUser(email: String, password: String): ResultEntity {
        try {
           FirebaseAuth.getInstance().getUserByEmail(email)
            return ResultEntity(true, "User logged in successfully")
        } catch (e: Exception) {
            e.printStackTrace()
            return ResultEntity(false, "Login failed: ${e.message}")
        }
    }
}