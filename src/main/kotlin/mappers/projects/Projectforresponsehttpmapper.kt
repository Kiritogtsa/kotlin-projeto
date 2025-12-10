package com.teste.mappers.projects

import com.teste.dtos.Project.ProjectResponseHTTP
import com.teste.models.Project

fun Project.toResponseHttp(): ProjectResponseHTTP {
    return ProjectResponseHTTP(
        id = this.id,
        nome = this.nome,
        tipo = this.tipo,
        status = this.status,
        progress = this.progress,
        descrction = this.descrction
    )
}