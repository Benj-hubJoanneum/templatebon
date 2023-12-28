package com.ssi.schaefer.lunchbon.service.api.model;

import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Lunchbon(
    var userId: String,
    var restaurant: String,
    var timestamp: Long,
    var timestampAsDateString: String?
) : BaseEntity() {
    constructor() : this("","",  System.currentTimeMillis(), null)

    // helper method to format timestamp
    fun formatTimeStamp(timeZoneId: String) {
        val zoneId = ZoneId.of(timeZoneId)
        val dateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        timestampAsDateString = dateTime.format(formatter)
    }
}