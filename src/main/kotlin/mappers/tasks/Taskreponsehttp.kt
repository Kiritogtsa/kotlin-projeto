package com.teste.mappers.tasks

import com.teste.dtos.task.TaskResponseHTTP
import com.teste.mappers.colaboradores.toColaboradorresponsehttp
import com.teste.mappers.projects.toResponseHttp
import com.teste.models.Task

fun Task.toTaskresponsehttp(): TaskResponseHTTP {
    return TaskResponseHTTP(
        id = this.id,
        title = this.title,
        project = this.project.toResponseHttp(),
        prazo = this.prazo.toString(),
        status = this.status,
        resposavel = this.resposavel.toColaboradorresponsehttp()
    )
}