package com.teste.dtos.colaborador

import kotlinx.serialization.Serializable

@Serializable
data class Colaboradorsessao(
    val id: Int?,
    val nome: String?,
    val email: String?,
    val cargo: String?
)