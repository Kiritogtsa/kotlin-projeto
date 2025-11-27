package com.teste.repository


import com.teste.mappers.colaboradores.mapFrom
import com.teste.mappers.colaboradores.toColaborador
import com.teste.models.Colaborador
import com.teste.tables.Colaboradores
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ColaboradorRepository {

    // Buscar todos os colaboradores
    fun GetAll(): Result<List<Colaborador>> {
        return try {
            val colaboradores = transaction {
                Colaboradores.selectAll().map { it.toColaborador() }
            }
            Result.success(colaboradores)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun update(colaborador: Colaborador): Result<Colaborador> {
        return try {
            val saved = transaction {
                Colaboradores.update({ Colaboradores.email eq colaborador.email }) {
                    colaborador.nome?.let { newNome -> it[nome] = newNome }
                    colaborador.cargo?.let { newCargo -> it[cargo] = newCargo }
                    colaborador.setor?.let { newSetor -> it[setor] = newSetor }
                    colaborador.status?.let { newStatus -> it[status] = newStatus }
                    colaborador.ramal?.let { newRamal -> it[ramal] = newRamal }
                    colaborador.habilidades?.let { newHabilidades -> it[habilidades] = newHabilidades }
                }
            }
            if (saved > 0)
                Result.success(colaborador)
            else
                Result.failure(Exception("Colaborador não encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun create(colaborador: Colaborador): Result<Colaborador> {
        return try {
            val saved = transaction {
                val insertStatement = Colaboradores.insert(Colaboradores.mapFrom(colaborador))
                val id = insertStatement[Colaboradores.id]
                Colaboradores.select(Colaboradores.columns)
                    .map { it.toColaborador() }
                    .firstOrNull { it.id == id }

            }
            if (saved != null) Result.success(saved)
            else Result.failure(Exception("Erro ao salvar colaborador"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Buscar colaborador por email
    fun getByEmail(email: String): Result<Colaborador> {
        return try {
            val colaborador = transaction {
                with(SqlExpressionBuilder) {
                    Colaboradores.select(Colaboradores.columns)
                        .map { it.toColaborador() }
                        .firstOrNull { it.email == email }
                }
            }
            if (colaborador != null) {
                Result.success(colaborador)
            } else {
                Result.failure(Exception("Colaborador não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
