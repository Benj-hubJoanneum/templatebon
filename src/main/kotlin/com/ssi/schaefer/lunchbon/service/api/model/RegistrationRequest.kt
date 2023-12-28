package com.ssi.schaefer.lunchbon.service.api.model

data class RegistrationRequest(
    val user: User,
    val credentials: UserCredentials
)
