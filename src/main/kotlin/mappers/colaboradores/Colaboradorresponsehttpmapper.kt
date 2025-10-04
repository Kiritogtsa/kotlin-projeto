package com.teste.mappers.colaboradores

import com.teste.dtos.colaborador.ColaboradorResponseHttp
import com.teste.models.Colaborador

fun Colaborador.toColaboradorresponsehttp(): ColaboradorResponseHttp {
    return ColaboradorResponseHttp(
        nome = this.nome,
        email = this.email,
        cpf = this.cpf,
        ramal = this.ramal,
        status = this.status,
        cargo = this.cargo,
        setor = this.setor,
        habilidades = this.habilidades,
    )
}