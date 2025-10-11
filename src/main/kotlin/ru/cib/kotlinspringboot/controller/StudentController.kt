package ru.cib.kotlinspringboot.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.kotlinspringboot.dto.Student
import ru.cib.kotlinspringboot.service.LoadFromXmlFile

@Controller
class StudentController(
    private val loadFromXmlFile: LoadFromXmlFile,
) {

    @GetMapping("/v1/students")
    fun getStudent(model: Model): String {
        val students = loadFromXmlFile.loadFromXmlFile()
        model.addAttribute("students", students)
        return "index"
    }
}