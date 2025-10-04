package com.teste.mappers.projects

import com.teste.models.Project
import com.teste.tables.Projects
import org.jetbrains.exposed.sql.statements.InsertStatement

fun Projects.mapFrom(project: Project): Projects.(InsertStatement<Number>) -> Unit = {
    it[nome] = project.nome
    it[tipo] = project.tipo
    it[status] = project.status
    it[progress] = project.progress
    it[descriction] = project.descrction
}

