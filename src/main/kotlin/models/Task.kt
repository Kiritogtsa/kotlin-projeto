package com.teste.models

import com.teste.models.enums.status.StatusTs
import kotlinx.datetime.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val project: Project,
    val prazo: LocalDate? = null,
    val status: StatusTs,
    val resposavel: Colaborador
)
