package com.ssi.schaefer.lunchbon.service

import com.ssi.schaefer.lunchbon.businesslogic.UserBusinessLogic
import com.ssi.schaefer.lunchbon.service.api.model.ResultEntity
import com.ssi.schaefer.lunchbon.service.api.model.User
import com.ssi.schaefer.lunchbon.service.api.model.DataResultEntity
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException

@Service
class UserService(
    private val userBusinessLogic: UserBusinessLogic) {

    fun createUser(userDetails: User): ResultEntity {
        return userBusinessLogic.insertUser(userDetails)
    }

    fun getUserById(id: String): User? {
        var user: User? = null
        userBusinessLogic.loadUserById(id) { loadedUser ->
            if (loadedUser != null) {
                user = loadedUser
            } else {

            }
        }
        return user
    }

    fun getUserByEmail(email: String): DataResultEntity<User> {
        return userBusinessLogic.loadUserByEmail(email)
    }

    fun getUserByToken(token: String): User? {
        return userBusinessLogic.loadUserByToken(token)
    }
    fun getAllUsers(): List<User?> {
        var users: List<User?> = emptyList()
        userBusinessLogic.loadAllUsers() { loadedUsers ->
            if(loadedUsers.isNotEmpty()){
                users = loadedUsers
            } else {

            }
        }
        return users
    }

    fun updateUser(id: String, updates: Map<String, Any?>): Boolean {
        // Load the user asynchronously
        val userFuture = CompletableFuture<User?>()
        userBusinessLogic.loadUserById(id) {
            userFuture.complete(it)
        }

        try {
            val user = userFuture.get() ?: return false

            // Update individual fields if present in the updates map
            updates["email"]?.let { user.email = it as String }
            updates["personalnumber"]?.let { user.personalnumber = it as String }

            // Update the user asynchronously
            val updateFuture = CompletableFuture<Boolean>()
            userBusinessLogic.updateUser(id, user) {
                updateFuture.complete(it)
            }

            return updateFuture.get()
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        } catch (e: ExecutionException) {
            e.printStackTrace() // handle the exception as needed
        }

        return false
    }

    fun deleteUser(id: String): Boolean {
        var success = false
        userBusinessLogic.deleteUser(id) { deleteSuccess ->
            success = deleteSuccess
        }
        return success
    }

}