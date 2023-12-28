package com.ssi.schaefer.lunchbon.acceslogic

import com.google.cloud.firestore.Firestore
import com.ssi.schaefer.lunchbon.acceslogic.irepository.ILunchbonRepository
import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.springframework.stereotype.Repository

@Repository
class LunchbonRepository(firestore: Firestore) : GenericRepository<Lunchbon>(  firestore, "lunchbon", Lunchbon::class.java),
ILunchbonRepository {

    private val lunchbonDao: GenericRepository<Lunchbon>
    private val collectionName = "lunchbon"

    init {
        val documentClass: Class<Lunchbon> = Lunchbon::class.java
        lunchbonDao = GenericRepository(firestore, collectionName, documentClass)
    }

    override fun getById(id: String): Lunchbon? {
        return lunchbonDao.getById(id)
    }

    override fun getAll(): List<Lunchbon?> {
        return lunchbonDao.getAll()
    }

    override fun insert(entity: Lunchbon) {
        lunchbonDao.insert(entity)
    }

    override fun delete(id: String) {
        lunchbonDao.delete(id)
    }
}