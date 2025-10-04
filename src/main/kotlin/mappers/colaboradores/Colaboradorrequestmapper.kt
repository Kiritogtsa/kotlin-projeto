package com.teste.mappers.colaboradores

import com.teste.dtos.colaborador.Colaboradorrequesthttp
import com.teste.models.Colaborador

fun Colaboradorrequesthttp.tocolaborador(): Colaborador {
    return Colaborador(
        id = null,
        nome = this.nome,
        cpf = this.cpf,
        cargo = this.cargo,
        setor = this.setor,
        senha = this.senha,
        status = this.status,
        email = this.email,
        ramal = this.ramal,
        habilidades = this.habilidades
    )
}
