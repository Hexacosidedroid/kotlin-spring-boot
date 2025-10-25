package ru.cib.kotlinspringboot.dao

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "students")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    var studentId: Long = 0

    @Column(name = "name", nullable = false, length = 50)
    var name: String? = null

    @Column(name = "surname", nullable = false, length = 50)
    var surname: String? = null

    @Column(name = "birthday", nullable = false)
    var birthday: LocalDate? = null

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var hobbies: List<Hobby> = emptyList()
}