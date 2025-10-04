package com.teste.mappers.projects

import com.teste.dtos.Project.Projectrequesthttp
import com.teste.models.Project

fun Projectrequesthttp.toProject(): Project {
    return Project(
        id = null,
        nome = this.nome,
        tipo = this.tipo,
        status = this.status,
        progress = this.progress,
        descrction = this.descrction,
    )
}