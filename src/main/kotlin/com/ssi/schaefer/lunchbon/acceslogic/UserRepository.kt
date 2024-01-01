package com.ssi.schaefer.lunchbon.acceslogic

import com.google.cloud.firestore.Firestore
import com.ssi.schaefer.lunchbon.acceslogic.irepository.IUserRepository
import com.ssi.schaefer.lunchbon.service.api.model.BaseEntity
import com.ssi.schaefer.lunchbon.service.api.model.User
import org.apache.commons.logging.Log
import org.springframework.stereotype.Repository

@Repository
class UserRepository(firestore: Firestore) : GenericRepository<User>( firestore, "user", User::class.java)
, IUserRepository {

    private val userDao: GenericRepository<User>
    private val collectionName = "user"

    init {
        val documentClass: Class<User> = User::class.java
        userDao = GenericRepository(firestore, collectionName, documentClass)
    }

    override fun getUserByToken(token: String): User? {
        val usersCollection = firestore.collection(collectionName)
        val query = usersCollection.whereEqualTo("token", token).limit(1)

        val querySnapshot = query.get().get()
        return if (!querySnapshot.isEmpty) {
            val userDocument = querySnapshot.documents[0]
            userDocument.toObject(User::class.java)
        } else {
            val newUser = User("", "")
            userDao.insert(newUser)
            newUser
        }
    }

    override fun getUserByEmail(email: String): User? {
        val usersCollection = firestore.collection(collectionName)
        val query = usersCollection.whereEqualTo("email", email).limit(1)

        val querySnapshot = query.get().get()
        return if (!querySnapshot.isEmpty) {
            val userDocument = querySnapshot.documents[0]
            val user = userDocument.toObject(User::class.java)
            if (user is BaseEntity) {
                user.id = userDocument.id
            }
            user
        } else {
            null
        }
    }

    override fun getUserByNumber(number: String): User? {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): User? {
        return userDao.getById(id)
    }

    override fun getAll(): List<User?> {
        return userDao.getAll()
    }

    override fun insert(entity: User) {
        userDao.insert(entity)
    }

    override fun update(id: String, entity: User) {
        userDao.update(id, entity)
    }

    override fun delete(id: String) {
        userDao.delete(id)
    }
}