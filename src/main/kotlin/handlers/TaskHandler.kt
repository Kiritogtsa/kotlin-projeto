package com.teste.handlers

import com.teste.controllers.TaskController
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*

class TaskHandler(
    private val taskController: TaskController = TaskController()
) {
    suspend fun getall(call: ApplicationCall) {
        val result = taskController.getall()
        val ip = call.request.headers["X-Forwarded-For"]
            ?.split(",")?.firstOrNull()
            ?: call.request.headers["X-Real-IP"]
            ?: call.request.local.remoteHost

        result.fold(
            onSuccess = { tasks ->
                val string = "ip: " + ip + " rota: /tasks/\n success"
                print(string)
                call.respond(HttpStatusCode.OK,tasks)
            },
            onFailure = { e ->
                val string = "ip: " + ip + " rota: /tasks/\n" + e
                print(e)
                call.respond(HttpStatusCode.ExpectationFailed,e.message.toString())
            }
        )
    }
}