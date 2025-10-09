package com.teste

import com.teste.dtos.colaborador.Colaboradorsessao
import com.teste.handlers.ColaboradorHandlers
import com.teste.routes.Projectrotas
import com.teste.routes.tasksroutes
import com.teste.routes.colaboradorrotas
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.time.Duration.Companion.days

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Sessions) {
        cookie<Colaboradorsessao>("user_session") {
            cookie.path = "/"
            cookie.maxAge = 1.days
        }
        // aqui vai ter outro cookie
        // por agora to meio sem idea para continuar
    }
    val colaboradorHandlers = ColaboradorHandlers()
    configureSerialization()
    configureDatabases()
    routing {
        // implementar agora o login usando o contoller dos colaboradores
        // eq no transcions do exposed e para fazer sql com where =

        get("/healtdb") {
            try {
                transaction { exec("SELECT 1") }
                call.respondText("Database OK")
            } catch (e: Exception) {
                call.respondText(
                    "Database FAIL: ${e.message}",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
        post("/login") {
            colaboradorHandlers.login(call)
        }
        colaboradorrotas()
        Projectrotas()
        tasksroutes()
    }
}