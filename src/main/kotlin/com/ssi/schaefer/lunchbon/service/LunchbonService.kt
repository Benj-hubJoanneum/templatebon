package com.ssi.schaefer.lunchbon.service

import com.ssi.schaefer.lunchbon.businesslogic.LunchbonBusinessLogic
import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class LunchbonService(private val lunchbonBusinessLogic: LunchbonBusinessLogic) {

    fun createLunchbon(lunchbon: Lunchbon) {
        val zoneId = ZoneId.of("Europe/Vienna")
        val timestamp = ZonedDateTime.now(zoneId).toInstant().toEpochMilli()

        lunchbon.timestamp = timestamp

        lunchbonBusinessLogic.insertLunchbon(lunchbon) { success ->
            if (success) {

            } else {

            }
        }
    }

    fun getLunchbonById(id: String): Lunchbon? {
        var lunchbon: Lunchbon? = null
        lunchbonBusinessLogic.loadLunchbonById(id) { loadedLunchbon ->
            if (loadedLunchbon != null) {
                lunchbon = loadedLunchbon
            } else {

            }
        }
        return lunchbon
    }

    fun getAllLunchbons(): List<Lunchbon?> {
        var lunchbons: List<Lunchbon?> = emptyList()
        lunchbonBusinessLogic.loadAllLunchbons { loadedLunchbons ->
            if (loadedLunchbons.isNotEmpty()) {
                lunchbons = loadedLunchbons
            } else {

            }
        }
        return lunchbons
    }

    fun updateLunchbon(id: String, lunchbon: Lunchbon): Boolean {
        var success = false
        lunchbonBusinessLogic.updateLunchbon(id, lunchbon) { updateSuccess->
            success = updateSuccess
        }
        return success
    }

    fun deleteLunchbon(id: String): Boolean {
        var success = false
        lunchbonBusinessLogic.deleteLunchbon(id) { deleteSuccess ->
            success = deleteSuccess
        }
        return success
    }
}