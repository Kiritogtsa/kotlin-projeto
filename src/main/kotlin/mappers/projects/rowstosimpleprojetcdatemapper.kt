package com.teste.mappers.projects

import com.teste.dtos.Project.ProjetosdataSimple
import com.teste.models.Project
import com.teste.tables.Equipeproject
import com.teste.tables.Projects
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProjetcdatesimple() = ProjetosdataSimple(
    nome = this[Projects.nome]!!,
    privilegios = this[Equipeproject.funcao]!!
)