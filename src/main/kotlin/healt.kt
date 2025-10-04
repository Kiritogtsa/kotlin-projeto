package com.teste

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.testDatabaseConnection() {
    try {
        transaction {
            exec("SELECT 1") { rs ->
                if (rs.next()) {
                    log.info("✅ Conexão com banco verificada!")
                }
            }
        }
    } catch (e: Exception) {
        log.error("❌ Erro ao conectar no banco: ${e.message}", e)
    }
}