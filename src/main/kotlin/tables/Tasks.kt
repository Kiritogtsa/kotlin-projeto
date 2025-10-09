package com.teste.tables

import com.teste.models.enums.status.StatusTs
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object Tasks : Table("tarefa") {
    val id = integer("id_tarefa").autoIncrement()
    val title = varchar("titulo", 100)
    val projectId = reference("id_projeto", Projects.id)
    val prazo = date("prazo")
    val status = enumerationByName("status", 30, StatusTs::class).default(StatusTs.`To-Do`)
    val responsavel = reference("responsavel", Colaboradores.id)
    override val primaryKey = PrimaryKey(id)
}
