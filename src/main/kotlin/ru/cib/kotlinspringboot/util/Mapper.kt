package ru.cib.kotlinspringboot.util

import ru.cib.kotlinspringboot.dao.Hobby
import ru.cib.kotlinspringboot.dao.Student
import ru.cib.kotlinspringboot.dto.Hobbies
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ru.cib.kotlinspringboot.dto.Student.toDao(): Student {
    val self = this
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = LocalDate.parse(self.birthday, formatter)
    val studentDB = Student().apply {
        this.name = self.name
        this.birthday = date
        this.surname = self.surname
    }
    return studentDB.apply {
        this.hobbies = self.hobbies?.toDao(studentDB)!!
    }
}

fun Hobbies.toDao(studentDB: Student): List<Hobby> {
    val self = this
    val listOfHobby = mutableListOf<Hobby>()
    self.hobby.forEach { hobbyXml ->
        val hobbyDB = Hobby().apply {
            hobby = hobbyXml
            student = studentDB
        }
        listOfHobby.add(hobbyDB)
    }
    return listOfHobby
}
