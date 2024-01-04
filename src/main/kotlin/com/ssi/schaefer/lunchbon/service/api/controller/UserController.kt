package com.ssi.schaefer.lunchbon.service.api.controller

import com.google.gson.Gson
import com.ssi.schaefer.lunchbon.service.FirebaseAuthService
import com.ssi.schaefer.lunchbon.service.HelperMethods
import com.ssi.schaefer.lunchbon.service.UserService
import com.ssi.schaefer.lunchbon.service.api.model.LoginRequest
import com.ssi.schaefer.lunchbon.service.api.model.RegistrationRequest
import com.ssi.schaefer.lunchbon.service.api.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    private val userService: UserService,
    private val firebaseAuthService: FirebaseAuthService,
    private val helperMethods: HelperMethods
) {

    val gson = Gson()

    @GetMapping("/userToken")
    fun getUserByToken(@RequestParam token: String): ResponseEntity<User> {
        val user = userService.getUserByToken(token)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/user")
    fun getUserById(@RequestParam id: String): User? {
        return userService.getUserById(id)
    }

    @GetMapping("/userByEmail")
    fun getUserByEmail(@RequestParam email: String): ResponseEntity<String> {
        val cleanedEmail = helperMethods.removeQuotesAndUnescape(email)
        val result = userService.getUserByEmail(cleanedEmail)

        return if(result.success && result.entityData != null ) {
            ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(result.entityData))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.message)
        }
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody request: String): ResponseEntity<String> {
        val cleanedRequest = helperMethods.removeQuotesAndUnescape(request)
        val registrationRequest = gson.fromJson(cleanedRequest, RegistrationRequest::class.java)
        val registrationResult = userService.createUser(registrationRequest.user)
        return if (registrationResult.success) {

            val email = registrationRequest.credentials.email
            val password = registrationRequest.credentials.password

            val firebaseRegistrationResult = firebaseAuthService.registerUser(email, password)

            return if (firebaseRegistrationResult.success) {
                ResponseEntity.ok(gson.toJson(firebaseRegistrationResult.message))
            } else {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(firebaseRegistrationResult.message)
            }
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationResult.message)
        }
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody request: String): ResponseEntity<String> {
        val cleanedRequest = helperMethods.removeQuotesAndUnescape(request)
        val loginRequest = gson.fromJson(cleanedRequest, LoginRequest::class.java)

        val email = loginRequest.credentials.email
        val password = loginRequest.credentials.password
        val loginResult = firebaseAuthService.authenticateUser(email, password)

        return if (loginResult.success) {
            ResponseEntity.ok(gson.toJson(loginResult.message))
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResult.message)
        }
    }

    //change
    @GetMapping("/users")
    fun getUsers(): List<User?> {
        return userService.getAllUsers()
    }

    @PutMapping("/user/update")
    fun updateUser(@RequestParam id: String, @RequestBody updates: Map<String, Any?>): ResponseEntity<Any> {
        val success = userService.updateUser(id, updates)
        return if (success) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @DeleteMapping("/user/delete")
    fun deleteUser(@RequestParam id: String): ResponseEntity<Any> {
        val success = userService.deleteUser(id)
        return if (success) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}

