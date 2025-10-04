package com.teste.tables

import com.teste.models.enums.status.StatusTs
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object Task : Table("tarefa") {
    val id = integer("id_tarefa").autoIncrement()
    val title = varchar("titulo", 100).nullable()
    val projectId = reference("id_projeto", Projects.id).nullable()
    val prazo = date("prazo").nullable()
    val status = enumerationByName("status", 30, StatusTs::class).default(StatusTs.`To-Do`).nullable()
    val responsavel = reference("responsavel", Colaboradores.id).nullable()
    override val primaryKey = PrimaryKey(id)
}