package com.teste.routes

import com.teste.handlers.Projecthanlder
import io.ktor.server.routing.*

fun Route.Projectrotas() {
    val Projecthanlder = Projecthanlder()
    route("/project") {
        get { Projecthanlder.getall(call) }
        get("/projectequipe/{nome}") { Projecthanlder.getcolaboradores(call) }
    }

}