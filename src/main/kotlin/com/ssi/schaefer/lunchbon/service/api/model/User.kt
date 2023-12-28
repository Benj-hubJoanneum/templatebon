package com.ssi.schaefer.lunchbon.service.api.model


data class User(
    var email: String,
    var personalnumber: String?
) : BaseEntity() {
    constructor() : this("", null)
}