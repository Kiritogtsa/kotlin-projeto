package com.teste.repository

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.teste.dtos.Project.ProjectResponseHTTP
import com.teste.dtos.Project.ProjetosdataSimple
import com.teste.mappers.colaboradores.toColaborador
import com.teste.mappers.projects.mapFrom
import com.teste.mappers.projects.toProject
import com.teste.mappers.projects.toProjetcdatesimple
import com.teste.mappers.projects.toResponseHttp
import com.teste.models.Colaborador
import com.teste.models.Project
import com.teste.tables.Colaboradores
import com.teste.tables.Equipeproject
import com.teste.tables.Projects
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ProjectReposity
{
    fun getall(): Result<List<Project>>
    {
        return try {
            val projects = transaction { Projects.selectAll().map { it.toProject() } }
            Result.success(projects)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun get(id: Int): Result<Project>
    {
        return try {
            val project = transaction {
                Projects.select(Projects.id eq id)
                    .map { it.toProject() }
                    .firstOrNull()
            }
            if (project != null) Result.success(project)
            else Result.failure(Exception("novo erro"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getbyname(nome: String): Result<Project>
    {
        return try {
            val project = transaction {
                with(SqlExpressionBuilder) {
                    Projects.select(Projects.columns)
                        .map { it.toProject() }
                        .firstOrNull { it.nome == nome }
                }
            }
            if (project != null) Result.success(project)
            else Result.failure(Exception("erro ao buscar pelo nome"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun insert(project: Project): Result<Project>
    {
        return try {
            val saved = transaction {
                val insertStatement = Projects.insert(Projects.mapFrom(project))
                val id = insertStatement[Projects.id]
                Projects.select(Projects.columns)
                    .map { it.toProject() }
                    .firstOrNull { it.id == id }
            }
            if (saved != null) Result.success(saved)
            else Result.failure(Exception("não foi possivel criar o projeto"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    fun getprivilegiosandprojetos(colaborador: Colaborador):Result<List<ProjetosdataSimple>>
    {
        return try {
            val id = colaborador.id!!
            val saved = transaction {
                (Equipeproject innerJoin Projects)
                    .select(Equipeproject.id_colaborador eq id)
                    .map { it.toProjetcdatesimple() }
            }
            Result.success(saved)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    fun insertuser(colaborador: Colaborador, project: Project, funcao: String)
    {
        transaction {
            Equipeproject.insert {
                it[this.id_project] = project.id!!
                it[this.id_colaborador] = colaborador.id!!
                it[this.funcao] = funcao
            }
        }
    }

    fun getcolaboradores(project: Project): Result<List<Colaborador>>
    {
        return try {
            val saved = transaction {
                (Equipeproject innerJoin Colaboradores)
                    .selectAll()
                    .filter { it[Equipeproject.id_project] == project.id }
            }
            Result.success(saved.map { it.toColaborador() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getProjectsonColaboradores(colaborador: Colaborador): Result<List<Project>>
    {
        return try {
            val saved = transaction{ (Equipeproject innerJoin Projects)
                .selectAll()
                .filter { it[Equipeproject.id_colaborador] == colaborador.id}
            }
            Result.success(saved.map { it.toProject() })
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}