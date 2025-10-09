package com.teste.dtos.task

import com.teste.dtos.Project.ProjectResponseHTTP
import com.teste.dtos.colaborador.ColaboradorResponseHttp
import com.teste.models.enums.status.StatusTs
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponseHTTP(
    val id: Int,
    val title: String,
    val project: ProjectResponseHTTP,
    val prazo: String? ,
    val status: StatusTs,
    val resposavel: ColaboradorResponseHttp
)

