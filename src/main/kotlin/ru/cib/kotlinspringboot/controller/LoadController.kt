package ru.cib.kotlinspringboot.controller

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import ru.cib.kotlinspringboot.service.LoadFromDatabase
import ru.cib.kotlinspringboot.service.LoadFromXmlFile
import ru.cib.kotlinspringboot.service.SaveToDatabase
import ru.cib.kotlinspringboot.util.toDto

@RestController
class LoadController(
    private val loadFromXmlFile: LoadFromXmlFile,
    private val saveToDatabase: SaveToDatabase,
    private val restTemplate: RestTemplate,
    private val loadFromDatabase: LoadFromDatabase,
    private val rabbitTemplate: RabbitTemplate
) {

    @GetMapping("/v1/loadStudents")
    fun loadStudents(): String {
        val students = loadFromXmlFile.loadFromXmlFile()
        students.student.forEach { student ->
            saveToDatabase.saveStudent(student)
        }
        return "ok"
    }

    @GetMapping("/v1/checkStudent")
    fun checkStudent(): String? {
        val student = loadFromDatabase.getStudents().asSequence().shuffled().find { true }
        val response = restTemplate.getForEntity("http://localhost:9090/student/check=${student?.name}", String::class.java)
        return response.body
    }

    @GetMapping("/v1/deleteFirstStudent")
    fun deleteFirstStudent(): String? {
        return loadFromDatabase.deleteStudent()
    }

    @GetMapping("/v1/sendStudentToQueue")
    fun sendToQueue(): String? {
        val student = loadFromDatabase.getStudents().asSequence().shuffled().find { true }
        rabbitTemplate.convertAndSend("tmp", student!!.toDto())
        return "sent to queue"
    }
}