package com.teste

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

//lateinit var database: Database
fun Application.configureDatabases() {
    val dbconfig = environment.config.config("ktor.database")
    Database.connect(
        url = dbconfig.property("url").getString(),
        user = dbconfig.property("user").getString(),
        driver = dbconfig.property("driver").getString(),
        password = dbconfig.property("password").getString(),
    )
//    try {
//        transaction(database) {
//            exec("SELECT 1")
//        }
//        log.info("✅ Conexão com banco estabelecida com sucesso!")
//    } catch (e: Exception) {
//        log.error("❌ Erro ao abrir o banco de dados", e) // passa a Exception, não e.message
//    }
}
