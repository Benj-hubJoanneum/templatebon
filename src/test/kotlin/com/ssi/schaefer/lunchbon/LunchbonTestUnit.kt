package com.ssi.schaefer.lunchbon

import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LunchbonTestUnit {

    @Test
    fun `create Lunchbon and check formatting`() {


        // Given
        val userId = "123"
        val restaurant = "Some Restaurant"
        val timestamp = 1703875770448
        val timeZoneId = "UTC"

        // When
        val lunchbon = Lunchbon(userId, restaurant, timestamp, null)

        // Then
        assertEquals(userId, lunchbon.userId)
        assertEquals(restaurant, lunchbon.restaurant)
        assertEquals(timestamp, lunchbon.timestamp)
        assertEquals(null, lunchbon.timestampAsDateString)

        // When
        lunchbon.formatTimeStamp(timeZoneId)

        // Then
        val expectedFormattedDateTime = "29-12-2023 18:49:30"
        assertEquals(expectedFormattedDateTime, lunchbon.timestampAsDateString)
    }
}
