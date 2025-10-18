package ru.cib.kotlinspringboot.dao

import java.util.Date

data class Student(
    var id: Int,
    var name: String,
    var surname: String,
    var birthday: Date,
    var hobbies: List<Hobbies>?
)

data class Hobbies(
    var hobby_id: Int,
    var student_id: Int,
    var hobby: String
)