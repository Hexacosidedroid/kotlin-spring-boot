package ru.cib.kotlinspringboot.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dao.Hobbies
import ru.cib.kotlinspringboot.dao.HobbiesRowMapper
import ru.cib.kotlinspringboot.dao.Student
import ru.cib.kotlinspringboot.dao.StudentRowMapper
import ru.cib.kotlinspringboot.dto.Students

@Service
class LoadFromDatabase (
    private val jdbcTemplate: JdbcTemplate
) {

    fun getStudents(): List<Student> {
        val students = jdbcTemplate.query(
            "SELECT student_id, name, surname, birthday FROM students",
            StudentRowMapper()
        )
        students.forEach { student ->
            val hobby = jdbcTemplate.query("SELECT hobby_id, student_id, hobby FROM hobbies WHERE student_id = ?", HobbiesRowMapper(), student.id)
            student.hobbies = hobby
        }
        return students
    }
}
