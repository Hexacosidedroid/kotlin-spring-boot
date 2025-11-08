package ru.cib.kotlinspringboot.service

import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dao.Student
import ru.cib.kotlinspringboot.repository.StudentRepository

@Service
class LoadFromDatabase (
    private val studentRepository: StudentRepository
) {

    fun getStudents(): List<Student> {
        val students = studentRepository.findAll()
        return students
    }

    fun deleteStudent(): String {
        val studentToDelete = studentRepository.findAll().first()
        studentRepository.delete(studentToDelete)
        return "ok"
    }
}
