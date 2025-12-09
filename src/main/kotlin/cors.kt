package com.teste
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.http.*

fun Application.configureCORS() {

    install(CORS) {

        allowCredentials = true

        // Libera localhost em TODAS AS PORTAS (Vite muda de porta)
        allowHost("localhost:5110", schemes = listOf("http"))
        allowHost("127.0.0.1:5110", schemes = listOf("http"))

        // Libera headers usados pelo React
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)

        // Libera métodos mais comuns
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Options)
    }
}