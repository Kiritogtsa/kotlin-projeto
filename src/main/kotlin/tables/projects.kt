package com.teste.tables

import com.teste.models.Typeproject
import com.teste.models.enums.status.StatusPj
import org.jetbrains.exposed.sql.Table

object Projects : Table("projeto") {
    val id = integer("id_projeto").autoIncrement()
    val nome = varchar("nome", 100).nullable()
    val tipo = enumerationByName("tipo", 20, Typeproject::class).default(Typeproject.Software).nullable()
    val status = enumerationByName("status", 30, StatusPj::class).default(StatusPj.`Em Andamento`).nullable()
    val progress = integer("progresso").nullable()
    val descriction = text("descricao").nullable()
    override val primaryKey = PrimaryKey(id)
}