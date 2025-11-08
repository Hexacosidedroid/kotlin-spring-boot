package ru.cib.kotlinspringboot.service

import org.springframework.stereotype.Service
import ru.cib.kotlinspringboot.xml.Student
import ru.cib.kotlinspringboot.repository.StudentRepository
import ru.cib.kotlinspringboot.util.toDao

@Service
class SaveToDatabase (
    private val studentRepository: StudentRepository
) {

    fun saveStudent(student: Student) {
        studentRepository.save(student.toDao())
    }
}