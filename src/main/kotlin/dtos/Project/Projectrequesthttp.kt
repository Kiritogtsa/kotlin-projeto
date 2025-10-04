package com.teste.dtos.Project

import com.teste.models.Typeproject
import com.teste.models.enums.status.StatusPj

data class Projectrequesthttp(
    val id: Int?,
    val nome: String?,
    val tipo: Typeproject?,
    val status: StatusPj?,
    val progress: Int?,
    val descrction: String?
)