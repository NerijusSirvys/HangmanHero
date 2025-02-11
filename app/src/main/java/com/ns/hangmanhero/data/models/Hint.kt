package com.ns.hangmanhero.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Hint(
    @Transient
    val id: String = "",
    val text: String,
    val strength: Strength,
)
