package com.teste.mappers.projects

import com.teste.models.Project
import com.teste.tables.Projects
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProject() = Project(
    id = this[Projects.id],
    nome = this[Projects.nome],
    tipo = this[Projects.tipo],
    status = this[Projects.status],
    progress = this[Projects.progress],
    descrction = this[Projects.descriction]
)