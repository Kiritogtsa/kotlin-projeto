package com.teste.dtos.colaborador

import com.teste.models.Colaborador

data class Loginstatus(
    val success: Boolean,
    val colaborador: Colaborador?,
)
