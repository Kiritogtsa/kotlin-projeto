package com.teste.mappers.colaboradores

import com.teste.models.Colaborador
import com.teste.tables.Colaboradores
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toColaborador() = Colaborador(
    id = this[Colaboradores.id],
    nome = this[Colaboradores.nome],
    cpf = this[Colaboradores.cpf],
    cargo = this[Colaboradores.cargo],
    setor = this[Colaboradores.setor],
    senha = this[Colaboradores.senha],
    status = this[Colaboradores.status],
    email = this[Colaboradores.email],
    ramal = this[Colaboradores.ramal],
    habilidades = this[Colaboradores.habilidades]
)
