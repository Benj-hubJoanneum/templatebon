package com.ssi.schaefer.lunchbon.acceslogic.irepository

import com.ssi.schaefer.lunchbon.service.api.model.User
import org.springframework.stereotype.Repository

@Repository
interface IUserRepository : IGenericRepository<User> {

    fun getUserByToken(token: String): User?

    fun getUserByEmail(email: String): User?

    fun getUserByNumber(number: String): User?
}