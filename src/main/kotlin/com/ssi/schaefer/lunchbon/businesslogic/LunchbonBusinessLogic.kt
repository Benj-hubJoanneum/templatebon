package com.ssi.schaefer.lunchbon.businesslogic

import com.ssi.schaefer.lunchbon.acceslogic.LunchbonRepository
import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.springframework.stereotype.Component

@Component
class LunchbonBusinessLogic(private val lunchbonRepository: LunchbonRepository) {

    fun insertLunchbon(lunchbon: Lunchbon, callback: (Boolean) -> Unit) {
        try {
            //var timestamp = System.currentTimeMillis()
            lunchbonRepository.insert(lunchbon)
            callback(true)
        } catch (e: Exception) {
            callback(false)
        }
    }

    fun loadLunchbonById(id: String, callback: (Lunchbon?) -> Unit) {
        try {
            val lunchbon = lunchbonRepository.getById(id)
            callback(lunchbon)
        } catch (e: Exception) {
            callback(null)
        }
    }

    fun loadAllLunchbons(callback: (List<Lunchbon?>) -> Unit) {
        try {
            val lunchbons = lunchbonRepository.getAll()
            callback(lunchbons)
        } catch (e: Exception) {
            callback(emptyList())
        }
    }

    fun updateLunchbon(id: String, lunchbon: Lunchbon, callback: (Boolean) -> Unit) {
        try {
            lunchbonRepository.update(id, lunchbon)
            callback(true)
        } catch (e: Exception) {
            callback(false)
        }
    }

    fun deleteLunchbon(id: String, callback: (Boolean) -> Unit) {
        try {
            lunchbonRepository.delete(id)
            callback(true)
        } catch (e: Exception) {
            callback(false)
        }
    }
}