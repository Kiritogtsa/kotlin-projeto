package com.teste.models

import com.teste.models.enums.status.Status

data class Colaborador(
    val id: Int?,
    val nome: String?,
    val cpf: String?,         // pode ser null
    val cargo: String?,       // pode ser null
    val setor: String?,       // pode ser null
    var senha: String?,
    val status: Status?,
    val email: String?,       // pode ser null
    val ramal: String?,       // pode ser null
    val habilidades: String?  // pode ser null
)
