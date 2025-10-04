package com.teste.handlers

import com.teste.controllers.ColaboradorController
import com.teste.dtos.colaborador.Colaboradorrequesthttp
import com.teste.dtos.colaborador.colaboradorhttp
import com.teste.mappers.colaboradores.tocolaboradorsessao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

// falta fazer o post do insert, o put do update eo del do deletar
class ColaboradorHandlers(private val colaborador: ColaboradorController = ColaboradorController()) {
    suspend fun login(call: ApplicationCall) {
        if (!call.request.contentType().match(ContentType.Application.Json)) {
            call.respondText("content-type errado", status = HttpStatusCode.NotAcceptable)
        }
        val colarequest = call.receive<colaboradorhttp>()
        val result = colaborador.login(colarequest)
        result.fold(
            onSuccess = { loginstatus ->
                if (loginstatus.success) {
                    var usersessao = loginstatus.colaborador?.tocolaboradorsessao()
                    call.sessions.set(usersessao)
                    call.respond(HttpStatusCode.OK, "sucesso ao fazer o login")
                } else {
                    call.respond(HttpStatusCode.NotAcceptable, "senha incorreta")
                }
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.InternalServerError, "Erro interno: ${e.message}")
            }
        )
    }

    suspend fun getall(call: ApplicationCall) {
        val result = colaborador.Getall()
        result.fold(
            onSuccess = { colaboraaders ->
                call.respond(HttpStatusCode.OK, colaboraaders)
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.InternalServerError, e)
            }
        )
    }

    // depois implementaar
    suspend fun createcolaborador(call: ApplicationCall) {
        if (!call.request.contentType().equals(ContentType.Application.Json)) {
            call.respond(HttpStatusCode.NotAcceptable, "o body não é formato json")
        }
        val colarequest = call.receive<Colaboradorrequesthttp>()
        val result = colaborador.create(colarequest)
        result.fold(
            onSuccess = { colaborador1 ->
                print("sucesso, usuario criado com sucesso")
                call.respond(HttpStatusCode.OK, colaborador1)
            },
            onFailure = { e ->
                val reposta = "deu um erro ao cria o colaborador: " + e.message
                println(reposta)
                call.respond(HttpStatusCode.InternalServerError, reposta)
            }
        )
    }

    // mesma coisa
    suspend fun upadtecolaborador(call: ApplicationCall) {

    }
}