package ru.cib.kotlinspringboot.dao

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class StudentRowMapper : RowMapper<Student> {
    override fun mapRow(rs: ResultSet, rowNum: Int) = Student(
        rs.getInt("student_id"),
        rs.getString("name"),
        rs.getString("surname"),
        rs.getDate("birthday"),
        hobbies = null
    )
}
