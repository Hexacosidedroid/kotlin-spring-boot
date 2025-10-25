package ru.cib.kotlinspringboot.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.cib.kotlinspringboot.dao.Student

interface StudentRepository : JpaRepository<Student, Long>