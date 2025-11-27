package com.teste.dtos.Project

import kotlinx.serialization.Serializable

@Serializable
data class ProjetosdataSimple(
    val nome:String,
    val privilegios:String
)