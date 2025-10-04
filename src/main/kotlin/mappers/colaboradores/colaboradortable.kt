package com.teste.mappers.colaboradores

import com.teste.models.Colaborador
import com.teste.tables.Colaboradores
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateBuilder

fun Colaboradores.mapFrom(colaborador: Colaborador): Colaboradores.(InsertStatement<Number>) -> Unit = {
    it[nome] = colaborador.nome
    it[email] = colaborador.email
    it[cpf] = colaborador.cpf
    it[ramal] = colaborador.ramal
    it[status] = colaborador.status
    it[senha] = colaborador.senha
    it[cargo] = colaborador.cargo
    it[setor] = colaborador.setor
    it[habilidades] = colaborador.habilidades
}

fun Colaboradores.mapFromUpdate(colaborador: Colaborador): Colaboradores.(UpdateBuilder<Int>) -> Unit = {
    colaborador.nome?.let { nomeCol -> it[nome] = nomeCol }
    colaborador.email?.let { emailCol -> it[email] = emailCol }
    colaborador.ramal?.let { ramalCol -> it[ramal] = ramalCol }
    colaborador.cargo?.let { cargoCol -> it[cargo] = cargoCol }
    colaborador.setor?.let { setorCol -> it[setor] = setorCol }
    colaborador.habilidades?.let { habCol -> it[habilidades] = habCol }
    // cpf e status, por exemplo, não são alterados aqui
}