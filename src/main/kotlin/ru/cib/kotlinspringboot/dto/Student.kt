package ru.cib.kotlinspringboot.dto

import java.time.LocalDate

data class Student (
    var studentId: Long? = null,
    var name: String? = null,
    var surname: String? = null,
    var birthday: LocalDate? = null,
    var hobbies: List<String?> = emptyList()
)