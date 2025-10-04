package com.teste.tables

import org.jetbrains.exposed.sql.Table

object Equipeproject : Table("equipe_projeto") {
    val id_project = reference("id_projeto", Projects.id)  // apenas Int
    val id_colaborador = reference("id_colaborador", Colaboradores.id) // referência correta
    val funcao = varchar("funcao", 50).nullable()

    override val primaryKey = PrimaryKey(id_project, id_colaborador)
}