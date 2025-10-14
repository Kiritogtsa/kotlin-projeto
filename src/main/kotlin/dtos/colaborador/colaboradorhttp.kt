package com.teste.dtos.colaborador

import kotlinx.serialization.Serializable

@Serializable
data class colaboradorhttp(
    val email: String,
    val password: String
)
