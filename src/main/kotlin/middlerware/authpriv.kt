package com.teste.middlerware

import com.teste.dtos.colaborador.Colaboradorsessao
import com.teste.repository.ColaboradorRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

val privelegios = arrayListOf("gestor", "admin")

// depois criar um plugin de log personalizado, e implementar mais mappers com
// alguma outra pasta para converter as classes em string para logar
fun Requirepriv(vararg roles: String) = createRouteScopedPlugin("requirepriv") {
    onCall { call ->
        val colaboradorsessao = call.sessions.get<Colaboradorsessao>()
        if (colaboradorsessao == null) {
            call.respond(HttpStatusCode.Unauthorized, "sem acesso")
            return@onCall
        }
        if (!roles.contains(colaboradorsessao.cargo)) {
            (
                    colaboradorsessao.cargo
                    )
            call.respond(HttpStatusCode.Unauthorized, "sem autorizaçaõ")
            return@onCall
        }
        ColaboradorRepository().getByEmail(colaboradorsessao.email!!).fold(
            onSuccess = { colaborador ->
                if(!(colaborador.cargo == colaboradorsessao.cargo && colaborador.email == colaboradorsessao.email && colaborador.nome == colaboradorsessao.nome)){
                    call.sessions.clear<Colaboradorsessao>()
                    call.respond(HttpStatusCode.Unauthorized,"o erro na sessão do usuario")
                    return@fold
                }
            },
            onFailure = { e->
                call.respond(HttpStatusCode.InternalServerError,e)
            }
        )
    }
}