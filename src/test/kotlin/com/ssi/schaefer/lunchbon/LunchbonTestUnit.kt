package com.ssi.schaefer.lunchbon

import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LunchbonTestUnit {

    @Test
    fun `create Lunchbon and check formatting`() {

        // params
        val userId = "123"
        val restaurant = "Some Restaurant"
        val timestamp = 1703875770448
        val timeZoneId = "UTC"

        // construct
        val lunchbon = Lunchbon(userId, restaurant, timestamp, null)

        // checks
        assertEquals(userId, lunchbon.userId)
        assertEquals(restaurant, lunchbon.restaurant)
        assertEquals(timestamp, lunchbon.timestamp)
        assertEquals(null, lunchbon.timestampAsDateString)

        // set time
        lunchbon.formatTimeStamp(timeZoneId)

        // temporary temp
        val expectedFormattedDateTime = "29-12-2023 18:49:30"

        // more checks
        assertEquals(expectedFormattedDateTime, lunchbon.timestampAsDateString)
    }
}
