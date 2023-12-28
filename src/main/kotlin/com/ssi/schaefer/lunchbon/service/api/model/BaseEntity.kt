package com.ssi.schaefer.lunchbon.service.api.model

import com.google.firebase.database.Exclude

abstract class BaseEntity {
    @Exclude
    var id: String = "" // Firebase works with String as ID
}
