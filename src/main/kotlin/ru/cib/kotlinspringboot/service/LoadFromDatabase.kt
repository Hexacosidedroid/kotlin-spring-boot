package ru.cib.kotlinspringboot.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dao.Student
import ru.cib.kotlinspringboot.repository.HobbyRepository
import ru.cib.kotlinspringboot.repository.StudentRepository

@Service
class LoadFromDatabase (
    private val studentRepository: StudentRepository
) {

    fun getStudents(): List<Student> {
        val students = studentRepository.findAll()
        return students
    }
}
