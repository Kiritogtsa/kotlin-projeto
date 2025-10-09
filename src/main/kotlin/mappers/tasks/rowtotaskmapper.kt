package com.teste.mappers.tasks

import com.teste.mappers.colaboradores.toColaborador
import com.teste.mappers.projects.toProject
import com.teste.models.Task
import com.teste.models.enums.status.StatusTs
import com.teste.tables.Tasks
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.totask() = Task(
    id = this[Tasks.id],
    title = this[Tasks.title] ?: "",
    project = this.toProject(),
    prazo = this[Tasks.prazo],
    status = this[Tasks.status] ?: StatusTs.Doing,
    resposavel = this.toColaborador(),
)