package ru.cib.kotlinspringboot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.kotlinspringboot.service.LoadFromXmlFile
import ru.cib.kotlinspringboot.service.SaveToDatabase

@RestController
class LoadController(
    private val loadFromXmlFile: LoadFromXmlFile,
    private val saveToDatabase: SaveToDatabase
) {

    @GetMapping("/v1/loadStudents")
    fun loadStudents(): String {
        val students = loadFromXmlFile.loadFromXmlFile()
        students.student.forEach { student ->
            val id = saveToDatabase.saveStudent(student)
            saveToDatabase.saveHobbies(student.hobbies, id!!)
        }
        return "ok"
    }
}