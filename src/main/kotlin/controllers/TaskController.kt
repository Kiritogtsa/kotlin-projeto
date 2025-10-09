package com.teste.controllers

import com.teste.dtos.task.TaskResponseHTTP
import com.teste.mappers.tasks.toTaskresponsehttp
import com.teste.repository.Taskrespository

class TaskController(
    private val repository: Taskrespository = Taskrespository()
) {
    fun getall(): Result<List<TaskResponseHTTP>> {
        try {
            val result = repository.getall()
            return result.fold(
                onSuccess = { tasks -> Result.success(tasks.map { it.toTaskresponsehttp() }) },
                onFailure = { e -> Result.failure(e) }
            )
        } catch (e: Exception) {
           return Result.failure(e)
        }
    }
}