package com.teste

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

//lateinit var database: Database
fun Application.configureDatabases() {
    val dbconfig = environment.config.config("ktor.database")

    val url = dbconfig.propertyOrNull("url")?.getString()
        ?: "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"  // fallback para testes
    val driver = dbconfig.propertyOrNull("driver")?.getString()
        ?: "org.h2.Driver"
    val user = dbconfig.propertyOrNull("user")?.getString()
        ?: "sa"
    val password = dbconfig.propertyOrNull("password")?.getString()
        ?: ""

    Database.connect(
        url = url,
        driver = driver,
        user = user,
        password = password
    )
}


