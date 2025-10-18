package ru.cib.kotlinspringboot.dao

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class HobbiesRowMapper : RowMapper<Hobbies> {
    override fun mapRow(rs: ResultSet, rowNum: Int) = Hobbies(
        rs.getInt("hobby_id"),
        rs.getInt("student_id"),
        rs.getString("hobby")
    )
}
