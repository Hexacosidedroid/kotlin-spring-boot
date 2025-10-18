package ru.cib.kotlinspringboot.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.dto.Hobbies
import ru.cib.kotlinspringboot.dto.Student
import java.sql.Date
import java.sql.Statement
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class SaveToDatabase (
    private val jdbcTemplate: JdbcTemplate
) {

    fun saveStudent(student: Student): Int? {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.parse(student.birthday, formatter)
        val sql = "Insert into students (name, surname, birthday) values (?, ?, ?)"
        jdbcTemplate.update ({ connection ->
            connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).apply {
                setString(1, "${student.name}")
                setString(2, "${student.surname}")
                setDate(3, Date.valueOf(date))
            }
        }, keyHolder)

        return (keyHolder.keys?.get("student_id")) as Int
    }

    fun saveHobbies(hobbies: Hobbies?, id: Int) {
        hobbies?.hobby?.forEach { hobby ->
            jdbcTemplate.update("Insert into hobbies (student_id, hobby) values ('${id}', '${hobby}')")
        }
    }
}