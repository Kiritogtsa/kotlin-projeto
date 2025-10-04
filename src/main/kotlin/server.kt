package com.teste

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class Server {
    fun Application.configureRouting() {
        routing {
            get("/") {
                call.respondText("Hello World!")
            }
        }
    }
}