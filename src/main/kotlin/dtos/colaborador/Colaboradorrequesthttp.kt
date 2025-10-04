package com.teste.dtos.colaborador

import com.teste.models.enums.status.Status

data class Colaboradorrequesthttp(
    val nome: String?,
    val email: String?,
    val cpf: String?,
    val ramal: String?,
    val status: Status = Status.Ativo,
    val cargo: String?,
    val senha: String?,
    val setor: String?,
    val habilidades: String?
)
