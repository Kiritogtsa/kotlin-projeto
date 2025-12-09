package com.teste.routes

import com.teste.handlers.ColaboradorHandlers
import com.teste.middlerware.RequireLoginPlugin
import com.teste.middlerware.Requirepriv
import io.ktor.server.routing.*

fun Route.colaboradorrotas(handler: ColaboradorHandlers = ColaboradorHandlers()) {
    route("/colaboradores") {
        // aqui vai fazer tudo para o gestor e o admin, como adicionar, atualizar e deletar outros usuarios
        // dai nos proximos controllers eu precessiria saber de algumas coisas do usuario
        // por dentro dessa rota não
        install(Requirepriv("gestor", "admin"))
        get { handler.getall(call) }
        post { handler.createcolaborador(call) }
        put { TODO() }
        delete { TODO() }
    }
    route("/colaborador") {
        install(RequireLoginPlugin)
        // aqui e a rote pessoal do colaborador, tanto o update dele mesmo vai vim por aqui

    }
    route("/me"){
        install(RequireLoginPlugin)
        get { handler.me(call) }
    }
    route("/login") {
        post{
            handler.login(call)
        }
    }
}