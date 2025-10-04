package com.teste.models

import com.teste.models.enums.status.StatusPj

data class Project(
    val id: Int?,
    val nome: String?,
    val tipo: Typeproject?,
    val status: StatusPj?,
    val progress: Int?,
    val descrction: String?
)
