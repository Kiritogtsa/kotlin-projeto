package com.teste.dtos.colaborador

import com.teste.models.enums.status.Status
import kotlinx.serialization.Serializable

@Serializable
data class ColaboradorResponseHttp(
    val nome: String?,
    val email: String?,
    val cpf: String?,
    val ramal: String?,
    val status: Status?,
    val cargo: String?,
    val setor: String?,
    val habilidades: String?
)
