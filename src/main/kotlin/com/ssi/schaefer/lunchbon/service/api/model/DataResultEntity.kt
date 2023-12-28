package com.ssi.schaefer.lunchbon.service.api.model

data class DataResultEntity<T>(
    val success: Boolean,
    val message: String,
    val entityData: T?
)
