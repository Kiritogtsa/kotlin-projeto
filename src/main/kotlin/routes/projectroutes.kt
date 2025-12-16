package com.teste.routes

import com.teste.handlers.Projecthanlder
import com.teste.middlerware.RequireLoginPlugin
import com.teste.middlerware.Requirepriv
import io.ktor.server.routing.*

fun Route.Projectrotas() {
    val Projecthanlder = Projecthanlder()
    route("/project") {
        install(RequireLoginPlugin)
        get { Projecthanlder.getall(call) }
        get("/projectequipe/{nome}") { Projecthanlder.getcolaboradores(call) }
        get("/me"){ Projecthanlder.getProjectsforuser(call)}
    }
   route("/project/admin"){
       install(Requirepriv("admin","gestor"))
   }
}