package com.teste.middlerware

import com.teste.dtos.colaborador.Colaboradorsessao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

val RequireLoginPlugin = createRouteScopedPlugin("RequieLogin") {
    onCall { call ->
        val session = call.sessions.get<Colaboradorsessao>()
        if (session == null) {
            call.respond(HttpStatusCode.Unauthorized, "Usuário não logado")
            return@onCall
        }
    }
}