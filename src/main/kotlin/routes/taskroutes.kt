package com.teste.routes

import com.teste.controllers.TaskController
import com.teste.handlers.TaskHandler
import io.ktor.server.routing.*

fun Route.tasksroutes() {
    val taskhandler: TaskHandler = TaskHandler()
    route("/tasks") {
        get { taskhandler.getall(call) }
    }
}