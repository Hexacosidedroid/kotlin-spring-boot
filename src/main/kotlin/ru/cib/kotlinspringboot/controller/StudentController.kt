package ru.cib.kotlinspringboot.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.cib.kotlinspringboot.xml.Hobbies
import ru.cib.kotlinspringboot.xml.Student
import ru.cib.kotlinspringboot.xml.Students
import ru.cib.kotlinspringboot.service.LoadFromDatabase

@Controller
class StudentController(
    private val loadFromDatabase: LoadFromDatabase
) {

    @GetMapping("/v1/students")
    fun getStudent(model: Model): String {
        val students = loadFromDatabase.getStudents()
        val mappedStudent = studentMapper(students)
        model.addAttribute("students", mappedStudent)
        return "index"
    }

    fun studentMapper(listOfStudentsFromDb: List<ru.cib.kotlinspringboot.dao.Student>): Students {
        val students = Students()
        val listOfStudents = mutableListOf<Student>()
        listOfStudentsFromDb.forEach {
            val hobbies = mutableListOf<String>()
            it.hobbies.forEach {
                hobbies.add(it.hobby!!)
            }
            val webStudent = Student(it.name, it.surname, it.birthday.toString(), Hobbies(hobbies))
            listOfStudents.add(webStudent)
        }
        students.student = listOfStudents
        return students
    }
}