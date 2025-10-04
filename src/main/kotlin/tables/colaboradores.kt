package com.teste.tables

import com.teste.models.enums.status.Status
import org.jetbrains.exposed.sql.Table

object Colaboradores : Table("colaborador") {
    val id = integer("id_colaborador").autoIncrement() // bate com SQL
    val nome = varchar("nome", 100).nullable()
    val cpf = varchar("cpf", 14).uniqueIndex().nullable()
    val cargo = varchar("cargo", 50).nullable()
    val setor = varchar("setor", 50).nullable()
    val senha = varchar("senha", 60).nullable()
    val status = enumerationByName("status", 20, Status::class).default(Status.Ativo).nullable()
    val email = varchar("email", 100).uniqueIndex().nullable()
    val ramal = varchar("ramal", 20).nullable()
    val habilidades = text("habilidades").nullable()

    override val primaryKey = PrimaryKey(id)
}