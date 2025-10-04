package com.teste.mappers.colaboradores

import com.teste.dtos.colaborador.Colaboradorsessao
import com.teste.models.Colaborador

fun Colaborador.tocolaboradorsessao(): Colaboradorsessao {
    return Colaboradorsessao(
        id = this.id,
        nome = this.nome,
        email = this.email,
        cargo = this.cargo
    )
}