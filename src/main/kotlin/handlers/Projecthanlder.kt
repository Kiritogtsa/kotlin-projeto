package com.teste.handlers

import com.teste.controllers.ProjetoController
import com.teste.dtos.Project.Projectpessoasrequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class Projecthanlder(
    private val contoller: ProjetoController = ProjetoController()
) {
    suspend fun getall(call: ApplicationCall) {
        val result = contoller.getall()
        result.fold(
            onSuccess = { projects ->
                call.respond(HttpStatusCode.OK, projects)
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.NotFound, e)
            }
        )
    }

    suspend fun getcolaboradores(call: ApplicationCall) {
        val nome = call.parameters["nome"] ?: ""
        if (nome == "") {
            call.respond(HttpStatusCode.NotAcceptable, "não foi possivel verificar o nome")
            return
        }
        val result = contoller.getcolaboradoresdoprojeto(
            Projectpessoasrequest(
                nomeprojeto = nome,
                nomecolaborador = null,
                funcao = null
            )
        )
        result.fold(
            onSuccess = { colaboradores ->
                call.respond(HttpStatusCode.OK, colaboradores)
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.NotAcceptable, " não foi possivel achar os colaboradores" + e.message)
            }
        )
    }
}