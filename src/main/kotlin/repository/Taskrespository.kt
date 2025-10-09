package com.teste.repository

import com.teste.mappers.tasks.totask
import com.teste.models.Task
import com.teste.tables.Colaboradores
import com.teste.tables.Projects
import com.teste.tables.Tasks
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class Taskrespository{
    fun getall():Result<List<Task>>{
        return try {
            val saved = transaction {
                (Tasks innerJoin Colaboradores innerJoin Projects)
                    .selectAll()
                    .map { it.totask() }
            }
            if(!saved.isEmpty()) Result.success(saved)
            else Result.failure(Exception("nao foi possivel achar nada no banco de dados"))
        }catch (e: Exception){
            Result.failure(e)
        }
    }
    fun create(){TODO()}
}
