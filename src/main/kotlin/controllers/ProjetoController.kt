package com.teste.controllers

import com.teste.dtos.Project.ProjectResponseHTTP
import com.teste.dtos.Project.Projectpessoasrequest
import com.teste.dtos.colaborador.ColaboradorResponseHttp
import com.teste.mappers.colaboradores.toColaboradorresponsehttp
import com.teste.mappers.projects.toResponseHttp
import com.teste.repository.ProjectReposity

class ProjetoController(
    private val respository: ProjectReposity = ProjectReposity()
) {
    fun getall(): Result<List<ProjectResponseHTTP>> {
        val result = respository.getall()
        return result.fold(
            onSuccess = { projects ->
                val projectsreponse = projects.map { it.toResponseHttp() }
                Result.success(projectsreponse)
            },
            onFailure = { e ->
                val texterror = "erro ao buscar os projetos: " + e.message
                print(texterror)
                Result.failure(Exception(texterror))
            }
        )
    }

    fun getcolaboradoresdoprojeto(projetorpessoasequest: Projectpessoasrequest): Result<List<ColaboradorResponseHttp>> {
        val nome = requireNotNull(projetorpessoasequest.nomeprojeto)
        return respository.getbyname(nome).fold(
            onSuccess = { project ->
                respository.getcolaboradores(project).fold(
                    onSuccess = { projects ->
                        Result.success(projects.map { it.toColaboradorresponsehttp() })
                    },
                    onFailure = { e ->
                        Result.failure(e)
                    }
                )
            },
            onFailure = { e ->
                Result.failure(e)
            }
        )
    }
}