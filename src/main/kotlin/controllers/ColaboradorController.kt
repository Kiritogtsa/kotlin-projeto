package com.teste.controllers

import at.favre.lib.crypto.bcrypt.BCrypt
import com.mysql.cj.log.Log
import com.teste.dtos.colaborador.ColaboradorResponseHttp
import com.teste.dtos.colaborador.Colaboradorrequesthttp
import com.teste.dtos.colaborador.Loginstatus
import com.teste.dtos.colaborador.colaboradorhttp
import com.teste.mappers.colaboradores.toColaboradorresponsehttp
import com.teste.mappers.colaboradores.tocolaborador
import com.teste.models.Colaborador
import com.teste.repository.ColaboradorRepository

class ColaboradorController(
    private val repository: ColaboradorRepository = ColaboradorRepository()
) {
    fun Getall(): Result<List<ColaboradorResponseHttp>> {
        val result = repository.GetAll()
        return result.fold(
            onSuccess = { colaboradores ->
                val colaboradoresresponse = colaboradores.map { it.toColaboradorresponsehttp() }
                Result.success(colaboradoresresponse)
            },
            onFailure = { e ->
                Result.failure(e)
            }
        )
    }

    fun update(colaboradorHttp: Colaboradorrequesthttp): Result<Loginstatus> {
        return try {
            print("update")
            if (colaboradorHttp.email == null || !colaboradorHttp.email.contains("@")) {
                return Result.failure(Exception("email inexistente ou invalido"))
            }
            val colaborador = colaboradorHttp.tocolaborador()
            val result = repository.update(colaborador)
            result.fold(
                onSuccess = { c ->
                    Result.success(Loginstatus(true,c))
                },
                onFailure = { e ->
                    print(e.message)
                    Result.failure(e)
                }
            )
        } catch (
            e: Exception
        ) {
            Result.failure(e)
        }
    }

    fun create(colaboradorhttp: Colaboradorrequesthttp): Result<Loginstatus> {
        return try {
            print("aqui")
            if (!(colaboradorhttp.email?.contains("@") ?: false) || (colaboradorhttp.nome?.length
                    ?: 0) < 5 || (colaboradorhttp.senha?.length ?: 0) < 8 || colaboradorhttp.cargo == "gestor"
            ) {
                return Result.success(Loginstatus(false, null))
            }
            var colaborador = colaboradorhttp.tocolaborador()
            val senha = BCrypt.withDefaults()
                .hashToString(10, (colaborador.senha ?: throw Exception("senha vazia")).toCharArray())
            colaborador.senha = senha
            print(colaborador.senha)
            val result = repository.create(colaborador)
            result.fold(
                onSuccess = { colaborador1 -> Result.success(Loginstatus(true, colaborador1)) },
                onFailure = { e ->
                    print(e.message)
                    Result.success(Loginstatus(false, null))
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun login(colaboradorHttp: colaboradorhttp): Result<Loginstatus> {
        return try {
            if (!colaboradorHttp.email.contains("@")) {
                return Result.success(Loginstatus(false, null))
            }

            val result = getbyemail(colaboradorHttp.email)

            // fold retorna diretamente LoginStatus
            val loginStatus = result.fold(
                onSuccess = { colaborador ->
                    val verifyResult = BCrypt.verifyer().verify(
                        colaboradorHttp.password.toCharArray(),
                        colaborador.senha?.toCharArray()
                    )
                    if (verifyResult.verified) {
                        print("senha correta")
                        Loginstatus(true, colaborador)
                    } else {
                        print("a senha nã bate")
                        Loginstatus(false, null)
                    }
                },
                onFailure = {
                    // qualquer falha no getByEmail retorna LoginStatus com false
                    Loginstatus(false, null)
                }
            )
            Result.success(loginStatus)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getbyemail(email: String): Result<Colaborador> {
        val result = repository.getByEmail(email)
        // depois fazer verificações aqui para se provar a classe e a função extra
        return result
    }
}