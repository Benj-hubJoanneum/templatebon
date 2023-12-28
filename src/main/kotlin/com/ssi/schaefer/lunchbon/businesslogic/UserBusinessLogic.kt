package com.ssi.schaefer.lunchbon.businesslogic

import com.ssi.schaefer.lunchbon.acceslogic.UserRepository
import com.ssi.schaefer.lunchbon.service.api.model.ResultEntity
import com.ssi.schaefer.lunchbon.service.api.model.User
import com.ssi.schaefer.lunchbon.service.api.model.DataResultEntity
import org.springframework.stereotype.Component

@Component
class UserBusinessLogic(private val userRepository: UserRepository) {

    fun insertUser(userDetails: User): ResultEntity {

        userRepository.insert(userDetails)
        return ResultEntity(true, "User registered successfully")
    }


    fun loadUserById(id: String, callback: (User?) -> Unit) {
        try {
            val user = userRepository.getById(id)
            callback(user)
        } catch (e: Exception) {
            callback(null)
        }
    }

    fun loadUserByToken(token: String): User? {
        return userRepository.getUserByToken(token)
    }

    fun loadUserByEmail(email: String): DataResultEntity<User> {
        val user = userRepository.getUserByEmail(email)
        return if (user != null) {
            DataResultEntity(true, "User fetched successfully", user)
        } else {
            DataResultEntity(false, "User not found", null)
        }
    }

    fun loadAllUsers(callback: (List<User?>) -> Unit) {
        try {
            val users = userRepository.getAll()
            callback(users)
        } catch (e: Exception) {
            callback(emptyList())
        }
    }

    fun updateUser(id: String, user: User, callback: (Boolean) -> Unit) {
        try {
            userRepository.update(id, user)
            callback(true)
        } catch (e: Exception) {
            callback(false)
        }
    }

    fun deleteUser(id: String, callback: (Boolean) -> Unit) {
        try {
            userRepository.delete(id)
            callback(true)
        } catch (e: Exception) {
            callback(false)
        }
    }
}