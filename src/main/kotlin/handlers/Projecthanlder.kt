package com.teste.handlers

import com.teste.controllers.ProjetoController
import com.teste.dtos.Project.Projectpessoasrequest
import com.teste.dtos.colaborador.Colaboradorrequesthttp
import com.teste.dtos.colaborador.Colaboradorsessao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

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

    suspend fun getProjectsforuser(call: ApplicationCall) {
        val colaboradorsessao = call.sessions.get<Colaboradorsessao>()
        if (colaboradorsessao == null) {
            call.respond(HttpStatusCode.Unauthorized, "Usuário não logado")
            return
        }
        return contoller.getProjetsforColaborador(colaboradorsessao.email!!).fold(
            onSuccess = { projetos ->
                call.respond(HttpStatusCode.OK, projetos)
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.InternalServerError, e)
            }
        )
    }

    suspend fun getProjectsofadmin(call: ApplicationCall) {
        if (!call.request.contentType().equals(ContentType.Application.Json)) {
            call.respond(HttpStatusCode.NotAcceptable, "o body não é formato json")
        }
        val colarequest = call.receive<Colaboradorrequesthttp>()
        val usersession = call.sessions.get<Colaboradorsessao>()
        if (usersession == null) {
            call.respond(HttpStatusCode.Unauthorized, "Usuário não logado")
            return
        }
        val privelegios = arrayListOf("admin", "gestor")
        if (!privelegios.contains(usersession.cargo)) {
            call.respond(HttpStatusCode.Unauthorized, "Usuário não possue autorização para isso")
            return
        }

        return contoller.getProjetsforColaborador(colarequest.email!!).fold(
            onSuccess = { projetos ->
                call.respond(HttpStatusCode.OK, projetos)
            },
            onFailure = { e ->
                call.respond(HttpStatusCode.InternalServerError, e)
            }
        )
    }
}